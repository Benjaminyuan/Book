package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class UDPServer{
    public static void main(String[] args)throws IOException {
       
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        byte[] data = new byte[10];
        DatagramPacket datagramPacket = new DatagramPacket(data,data.length);
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData(),0,datagramPacket.getLength()));
    }
}