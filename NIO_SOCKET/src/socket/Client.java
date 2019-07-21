package socket;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
public class Client{
    public static void main(String[] arg){
        Executor pool = Executors.newFixedThreadPool(4);
        try{
            for(int i =0;i<10;i++){
                pool.execute(new RunableClient(8008));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class RunableClient implements Runnable  {
    private Socket socket ;
    public RunableClient(int port){
        try{
            this.socket = new  Socket("localhost",port);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        try{
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[2014];
            int readLength = inputStream.read(data);
            while(readLength != -1 ){
                System.out.println(new String(data,0,readLength));
                readLength = inputStream.read(data);
            }
            inputStream.close();
            this.socket.close();
         }catch(IOException e){
             e.printStackTrace();
         }
    }
}