package buffer;
import java.nio.Buffer;
import java.nio.ByteBuffer;
public class TestBuffer{
    byte[] byteArray = new byte[]{1,2,3,4,6};
    ByteBuffer byteBuffer =  ByteBuffer.wrap(byteArray);
    ByteBuffer byteBuffer2 = ByteBuffer.allocate(10);
    System.out.println(byteBuffer.mark());
}