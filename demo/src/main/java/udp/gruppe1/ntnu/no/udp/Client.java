package udp.gruppe1.ntnu.no.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.UnexpectedException;

public class Client {

    private Client(){}

    public static DatagramPacket sendAndReceive(String hostname, int port) throws UnexpectedException {
    try{
        InetAddress address = InetAddress.getByName(hostname);
        DatagramSocket socket = new DatagramSocket();
        byte[] buffer = new byte[512];
 
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(request);
    
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);
        
        return response;
    }catch(Exception e){
        throw new UnexpectedException("Some of your data may be wrong");
    }
    }
}
 