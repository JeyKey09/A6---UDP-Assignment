package udp.gruppe1.ntnu.no.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.UnexpectedException;

public class Client {

    private Client() {
    }

    public static String sendAndReceive(String hostname, int port, String message)
            throws UnexpectedException, IllegalArgumentException {
        if (port < 0 || hostname == null || hostname.isEmpty() || message.isEmpty()) {
            throw new IllegalArgumentException("Some of the argument is not valid");
        }
        byte[] buffer = new byte[1024];

        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            DatagramPacket request = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.send(request);
                socket.receive(response);
            }
            return new String(response.getData(), 0, response.getLength());
        } catch (Exception e) {
            throw new UnexpectedException("Some of your data may be wrong");
        }
    }
}
