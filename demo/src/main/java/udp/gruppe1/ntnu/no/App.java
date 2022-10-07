package udp.gruppe1.ntnu.no;

import udp.gruppe1.ntnu.no.udp.Client;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String hostadress = "129.241.152.12";
        int port = 1234;
        try {
            String task = Client.sendAndReceive(hostadress, port, "task");
            String responseMessage = isQuestion(task)+" "+wordCount(task);
            if(Client.sendAndReceive(hostadress, port, responseMessage).equals("ok")){
                System.out.println("We did it!");
            } else{
                System.out.println("We did not do it!");
            }
        } catch (Exception e) {
            System.out.println("We fucked up!");
        }
    }

    public static int wordCount(String text){
        if(text.isBlank()){
            return 0;
        }
        if(!text.contains(" ")){
            return 1;
        }
        return text.split(" ").length;
    }

    public static String isQuestion(String text){
        if(text.contains("?")){
            return "question";
        }
        if(text.contains("!")){
            return "statement";
        }
        return "neither";
    }

}
