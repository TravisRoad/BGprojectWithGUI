package transport;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    ServerSocket service;
    private final int DEFAULT_PORT = 1100;
    private final int MAX_CLIENT_CNT = 5;
    private final String DEFAULT_IP = "127.0.0.1";

    public void start(){
        try{
            InetAddress address = InetAddress.getByName(DEFAULT_IP);
            Socket connectSocket = null;
            ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENT_CNT);
            service = new ServerSocket(DEFAULT_PORT,MAX_CLIENT_CNT,address);
            while(true){
                connectSocket = service.accept();
                //创建一个任务,放入线程池等待运行
                ServiceTask serviceTask = new ServiceTask(connectSocket);
                pool.execute(serviceTask);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ServiceTask implements Runnable{
        private Socket socket;

        ServiceTask(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {

        }
    }
}
