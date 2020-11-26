package transport;

import model.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    ServerSocket service;

    public void start(){
        try{
            String DEFAULT_IP = "127.0.0.1";
            InetAddress address = InetAddress.getByName(DEFAULT_IP);
            Socket connect = null;
            int MAX_CLIENT_CNT = 5;
            ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENT_CNT);
            int DEFAULT_PORT = 1100;
            service = new ServerSocket(DEFAULT_PORT, MAX_CLIENT_CNT,address);
            while(true){
                connect = service.accept();
                System.out.println(connect.toString());
                //创建一个任务,放入线程池等待运行
                ServiceTask serviceTask = new ServiceTask(connect);
                pool.execute(serviceTask);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ServiceTask implements Runnable{
        private Socket socket;
        private ObjectInputStream obj_is;
        private ObjectOutputStream obj_os;

        ServiceTask(Socket socket) throws IOException {
            this.socket = socket;
            obj_is = new ObjectInputStream(socket.getInputStream());
            obj_os = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            System.out.println("start");
            User user = (User) readObj();
            System.out.println(user);
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
                return obj_is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.start();
    }


}
