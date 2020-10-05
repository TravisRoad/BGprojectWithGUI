package transport;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public Socket socket = null;
    private final int DEFAULT_PORT = 1100;
    private final String DEFAULT_IP = "127.0.0.1";

    public Client(){
        try{
            socket = new Socket(DEFAULT_IP,DEFAULT_PORT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Client(String ip, int port){
        try{
            socket = new Socket(ip,port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean connect(String ip, int port){
        boolean flag = false;
        try{
            socket = new Socket(ip,port);
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

}
