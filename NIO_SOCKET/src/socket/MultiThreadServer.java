package socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MultiThreadServer {
    private ServerSocket server;
    private Executor pool;
    public MultiThreadServer(int port) throws IOException{
        this.server = new ServerSocket(port);
        this.pool = Executors.newFixedThreadPool(4);
    }
    public void startService() throws IOException{
        for(;;){
            Socket socket = server.accept();
            pool.execute(new Service(socket));
        }
    }
    public static void main(String[] args) throws IOException {
        MultiThreadServer multiThreadServer = new MultiThreadServer(8008);
        multiThreadServer.startService();
    }
}
class Service implements Runnable{
    private Socket socket ;
    public Service(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            OutputStream out = socket.getOutputStream();
            out.write(Thread.currentThread().getName().getBytes());
            out.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}