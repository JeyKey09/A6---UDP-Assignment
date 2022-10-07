package udp.gruppe1.ntnu.no;

import udp.gruppe1.ntnu.no.udp.Client;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            System.out.println(Client.sendAndReceive("129.241.152.12", 1234, "task"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
