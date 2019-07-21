package socket;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.File;
public class ImgClient{
    public static void main(String[] arg) throws IOException{
        
        Socket socket = new Socket("localhost",8008);
        File img = new File("./out.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(img);
        InputStream  socketInputStream = socket.getInputStream();
        byte[] imgData = new  byte[2024];
        int readLength = socketInputStream.read(imgData);
        while(readLength != -1){
            fileOutputStream.write(imgData,0,readLength);
            readLength = socketInputStream.read(imgData);
        }
        socketInputStream.close();
        fileOutputStream.close();
        socket.close();
    }
}