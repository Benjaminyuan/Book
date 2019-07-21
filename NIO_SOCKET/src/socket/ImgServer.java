package socket;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
public class ImgServer{
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(8008);
        File imgFile = new File("/Users/mac/Documents/Java/Book/NIO_SOCKET/input.jpg");
        FileInputStream fileInputStream = new FileInputStream(imgFile);
        byte[] imgData = new byte[2024];
        Socket client = server.accept();
        OutputStream outputStream = client.getOutputStream();
        int readLength = fileInputStream.read(imgData);
        while(readLength != -1){
            outputStream.write(imgData,0,readLength);
            readLength = fileInputStream.read(imgData);
        }
        fileInputStream.close();
        outputStream.close();
        client.close();
        server.close();
    }
}