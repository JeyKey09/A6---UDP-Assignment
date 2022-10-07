package udp.gruppe1.ntnu.no.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Client {

    private Client(){}

    public static DatagramPacket sendAndReceive(String hostname, int port) {
    
    InetAddress address = InetAddress.getByName(hostname);
    DatagramSocket socket = new DatagramSocket();
 
    byte[] buffer = new byte[512];
 
    DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
    socket.send(request);

    DatagramPacket response = new DatagramPacket(buffer, buffer.length);
    return socket.receive(response);
    }
}
 