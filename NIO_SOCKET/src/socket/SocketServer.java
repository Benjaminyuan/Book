package socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
public class SocketServer{
   public static void main(String[] arg){
    try{
        char[] charArray = new char[1024];
        System.out.println("Server end"+System.currentTimeMillis());
        ServerSocket socket = new ServerSocket(8088);
        Socket client = socket.accept();
        InputStream clientStream = client.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(clientStream);
        int readeLength = inputStreamReader.read(charArray);
        while(readeLength != -1){
            String str = new String(charArray,0,readeLength);
            System.out.println(str);
            readeLength = inputStreamReader.read(charArray);
        }
        System.out.println("Server end"+System.currentTimeMillis());
        inputStreamReader.close();
        clientStream.close();
        client.close();
        socket.close();
   }catch(Exception e){
       e.printStackTrace();
   }
}
}