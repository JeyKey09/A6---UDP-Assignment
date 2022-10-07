package udp.gruppe1.ntnu.no;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testWordCount(){
        assertEquals(1, App.wordCount("NTNU."));
        assertEquals(0, App.wordCount("."));
        assertEquals(4, App.wordCount("We are the champions."));
    }
}
