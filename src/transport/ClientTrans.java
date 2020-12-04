package transport;

import model.User;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;

/**
 * 用于客户端与服务端通信
 *
 * @author Travis
 */
public class ClientTrans {
    public Socket client = null;

    private ObjectInputStream obj_is;
    private ObjectOutputStream obj_os;


    public ClientTrans() {
    }

    /**
     * 连接
     *
     * @return 是否连接成功
     */
    public boolean connect() {
        int DEFAULT_PORT = 1100;
        String DEFAULT_IP = "127.0.0.1";
        return connect(DEFAULT_IP, DEFAULT_PORT);
    }

    /**
     * 连接到服务端
     *
     * @param ip   ip地址，默认为本地
     * @param port 端口
     * @return 是否连接成功
     */
    public boolean connect(String ip, int port) {
        boolean flag = false;
        try {
            client = new Socket(ip, port);
            InputStream is = client.getInputStream();
            obj_os = new ObjectOutputStream(client.getOutputStream());
            obj_os.flush();
            obj_is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 向Socket对象的输出流写入传输对象
     *
     * @param obj 待传输的对象
     * @return 传输是否成功
     */
    public boolean writeObj(Object obj) {
        boolean flag = false;
        try {
            obj_os.writeObject(obj);
            obj_os.flush();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 从Socket对象的输入流读出传输的对象
     *
     * @return 传输对象
     */
    public Object readObj() {
        try {
            Object obj = obj_is.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ClientTrans c = new ClientTrans();
        System.out.println("hello1");
        c.connect();
        System.out.println("hello");
        c.writeObj(new User("LXY", "lalala", 1234));
    }

//    public void setCurrentUser(User currentUser) {
//        this.currentUser = currentUser;
//    }
//
//    public User getCurrentUser() {
//        return currentUser;
//    }
}
