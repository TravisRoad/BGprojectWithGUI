package transport;

import model.User;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;

/**
 * 用于客户端与服务端通信
 * @author Travis
 */
public class ClientTrans {
    public Socket client = null;

    private User currentUser;
    private final char END_CHAR = '#';
    private InputStream is;
    private ObjectInputStream obj_is;
    private ObjectOutputStream obj_os;


    public ClientTrans(){}

    public boolean connect(){
        int DEFAULT_PORT = 1100;
        String DEFAULT_IP = "127.0.0.1";
        return connect(DEFAULT_IP, DEFAULT_PORT);
    }

    public boolean connect(String ip, int port){
        boolean flag = false;
        try{
            client = new Socket(ip,port);
            InputStream is = client.getInputStream();
            obj_os = new ObjectOutputStream(client.getOutputStream());
            obj_os.flush();
            obj_is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    public String readStr(){
        int len;
        String recvMsg = "";

        try {
            byte[] buffer = new byte[1024];
            while((len = is.read(buffer))!= -1){
                recvMsg = recvMsg.concat(new String(buffer, 0, len));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return recvMsg;
    }

    public boolean writeStr(@NotNull String str){
        boolean flag = false;
        try {
            OutputStream out = client.getOutputStream();
            out.write(str.getBytes("utf-8"));
            flag = true;
            client.shutdownOutput();
        }catch(IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean writeObj(Object obj){
        boolean flag = false;
        try {
            obj_os.writeObject(obj);
            obj_os.flush();
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    public Object readObj(){
        try{
            Object obj = obj_is.readObject();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ClientTrans c = new ClientTrans();
        System.out.println("hello1");
        c.connect();
        System.out.println("hello");
        c.writeObj(new User("LXY","lalala", 1234));
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
