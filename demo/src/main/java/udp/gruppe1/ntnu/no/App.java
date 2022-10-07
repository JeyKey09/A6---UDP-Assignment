package udp.gruppe1.ntnu.no;

import java.lang.reflect.Array;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public int wordCount(String text){
        if(text.isBlank()){
            return 0;
        }
        if(!text.contains(" ")){
            return 1;
        }
        return text.split(" ").length;
    }

    public String isQuestion(String text){
        if(text.contains("?")){
            return "Question";
        }
        if(text.contains("!")){
            return "Statement";
        }
        return "neither";
    }

}
