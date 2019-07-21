package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.InetSocketAddress;

public class UDPClient{
    public static void main(String[] arg) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.connect(new InetSocketAddress("localhost", 8888));
        byte[] data = "11111111333".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length);
        datagramSocket.send(packet);
        datagramSocket.close();

    }
}