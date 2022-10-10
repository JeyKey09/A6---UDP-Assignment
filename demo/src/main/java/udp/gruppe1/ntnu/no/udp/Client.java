package udp.gruppe1.ntnu.no.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.UnexpectedException;

/**
 * Client is used for Assignment A6 as a UDP sender.
 * It creates a socket for opening
 */
public class Client implements java.lang.AutoCloseable {

    private DatagramSocket socket;

    /**
     * Creates a new instance of a client
     * 
     * @throws SocketException if it can't create a socket
     */
    public Client() throws SocketException {
        this.socket = new DatagramSocket();
    }

    /**
     * Sends and receives a package between the {@hostname} and the client and
     * returns the message
     * 
     * @param hostname The ip-adresse or hostadresse
     * @param port     The port to send it at
     * @param message  A optional message to be sent to the receiver
     * @return The message sent from the server
     * @throws UnexpectedException      if it can't send the package
     * @throws IllegalArgumentException if the port is less then 0, the hostname is
     *                                  null or hostname is empty
     */
    public String sendAndReceive(String hostname, int port, String message)
            throws UnexpectedException, IllegalArgumentException {
        if (port < 0 || hostname == null || hostname.isEmpty()) {
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

    /**
     * A private function for the actual sending and receiving to make
     * sendAndReceive have less complexity.
     * 
     * It has a static buffer on 1024 Bytes
     * 
     * @param request A DatagramPacket to be sent
     * @return Returns a DatagramPacket from the server
     * @throws UnexpectedException if it can't send/receive the package or if the
     *                             socket has other problem
     */
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
