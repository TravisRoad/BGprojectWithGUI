package transport;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;

/**
 * 用于客户端与服务端通信
 * @author Travis
 */
public class Client {
    public Socket client = null;
    private final int DEFAULT_PORT = 1100;
    private final String DEFAULT_IP = "127.0.0.1";
    private final char END_CHAR = '#';
    private InputStream is;

    public Client(){}

    public boolean connect(){
        return connect(DEFAULT_IP,DEFAULT_PORT);
    }

    public boolean connect(String ip, int port){
        boolean flag = false;
        try{
            client = new Socket(ip,port);
            InputStream is = client.getInputStream();
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
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

}
