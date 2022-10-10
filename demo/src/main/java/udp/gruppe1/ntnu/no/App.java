package udp.gruppe1.ntnu.no;

import java.rmi.UnexpectedException;

import udp.gruppe1.ntnu.no.udp.Client;

/**
 * Gets and completes the task, and answers the server 3 times with the answer
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        try (Client client = new Client()) {
            String hostadress = "129.241.152.12";
            int port = 1234;
            for (int i = 0; i < 3; i++) {
                String task = client.sendAndReceive(hostadress, port, "task");
                String responseMessage = isQuestion(task) + " " + wordCount(task);
                if (client.sendAndReceive(hostadress, port, responseMessage).equals("ok")) {
                    System.out.println("We did it!");
                } else {
                    throw new UnexpectedException(
                            "We fucked up on the task" + task + "\n Sent response as: " + responseMessage);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Finding the amount of word contained in a sentence
     * 
     * @param text The sentence to find the word count
     * @return A integer with the amount of words found in the sentence
     */
    public static int wordCount(String text) {
        int count = 0;
        if (text.split(" ").length > 1) {
            count = text.split(" ").length;
        } else if (text.matches(".*[a-zA-Z].*")) {
            count = 1;
        }
        return count;

    }

    /**
     * Finds out if a sentence is a question or a statement
     * 
     * @param text the text to check
     * @return String with the value "statement" or "question"
     */
    public static String isQuestion(String text) {
        String answer = "statement";
        if (text.contains("?")) {
            answer = "question";
        }
        return answer;
    }

}
