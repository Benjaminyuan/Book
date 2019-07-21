package channel;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
public class ChannelTest{
    public static void main(String[] arg) throws IOException , InterruptedException{
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./data.txt"));
        FileChannel fileChannel = fileOutputStream.getChannel();
        try{
            ByteBuffer bf = ByteBuffer.wrap("abcdefg".getBytes());
            System.out.println("fileChannel.position:"+fileChannel.position());
            System.out.println("write1:"+fileChannel.write(bf));
            System.out.println("fileChannel.position:"+fileChannel.position());
            fileChannel.position(2);
            bf.rewind();
            bf.limit(5);
            System.out.println("write2:"+fileChannel.write(bf));
            System.out.println("fileChannel.position:"+fileChannel.position());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            fileChannel.close();
            fileOutputStream.close();
        }
    }
}