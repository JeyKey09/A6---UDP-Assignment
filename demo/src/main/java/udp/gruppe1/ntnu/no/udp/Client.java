package udp.gruppe1.ntnu.no.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.UnexpectedException;

public class Client implements java.lang.AutoCloseable {

    private DatagramSocket socket;

    public Client() throws UnexpectedException {
        try {
            this.socket = new DatagramSocket();
        } catch (Exception e) {
            throw new UnexpectedException("Couldn't create socket");
        }
    }

    public String sendAndReceive(String hostname, int port, String message)
            throws UnexpectedException, IllegalArgumentException {
        if (port < 0 || hostname == null || hostname.isEmpty() || message.isEmpty()) {
            throw new IllegalArgumentException("Some of the argument is not valid");
        }
        DatagramPacket response;
        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramPacket request = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
            response = useSocket(request);
        } catch (Exception e) {
            throw new UnexpectedException("Wasn't able to send/receive message");
        }
        return new String(response.getData(), 0, response.getLength());
    }

    private DatagramPacket useSocket(DatagramPacket request) throws UnexpectedException {
        byte[] buffer = new byte[1024];
        DatagramPacket response;
        try {
            response = new DatagramPacket(buffer, buffer.length);
            this.socket.send(request);
            this.socket.receive(response);
            return response;
        } catch (Exception e) {
            throw new UnexpectedException("Something went wrong when sending/receiving the packet");
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
