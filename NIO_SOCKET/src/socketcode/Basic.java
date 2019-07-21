package socketcode;
import java.net.*;
import java.util.Enumeration;
public class Basic{
    public static void main(String[] args) throws SocketException{
        Enumeration<NetworkInterface> nEnumeration = NetworkInterface.getNetworkInterfaces();
        while(nEnumeration.hasMoreElements()){
            NetworkInterface tempInterface = nEnumeration.nextElement();
            System.out.printf("网络名字:%s\n",tempInterface.getName());
            System.out.println("getMTU:"+tempInterface.getMTU());
        }
    }
}