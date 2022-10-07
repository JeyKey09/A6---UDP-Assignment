package udp.gruppe1.ntnu.no;

import java.rmi.UnexpectedException;

import udp.gruppe1.ntnu.no.udp.Client;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnexpectedException
    {
        try(Client client = new Client()) {
            String hostadress = "129.241.152.12";
            int port = 1234;
            for(int i = 0; i < 3; i++){
                try {
                    String task = client.sendAndReceive(hostadress, port, "task");
                    String responseMessage = isQuestion(task)+" "+wordCount(task);
                    if(client.sendAndReceive(hostadress, port, responseMessage).equals("ok")){
                        System.out.println("We did it!");
                    }
                    else{
                        throw new UnexpectedException("We fucked up on the task"+task+"\n Sent response as: "+responseMessage);
                    }
                } catch (Exception e) {
                    System.out.println("We fucked up!");
                }
            }
        } catch (Exception e) {
            throw new UnexpectedException("Something went wrong");
        } 
    }

    public static int wordCount(String text){
        System.out.println("-" + text + "-");
        if(text.split(" ").length > 1){
            return text.split(" ").length;
        }
        if(text.matches("[a-z A-Z]*")){
            System.out.println("i got here");
            return 1;
        }
        return 0;
        
    }

    public static String isQuestion(String text){
        if(text.contains("?")){
            return "question";
        }
        return "statement";
    }

}
