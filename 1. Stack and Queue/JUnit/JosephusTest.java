/**
 * @Author: Adam Fenjiro
 * This is an JUnit test for Josephus.
 * 1/31/2023
 * CS2321
 */
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import cs2321.Josephus;

public class JosephusTest {

	@Test
	public void testOrder() {
		Josephus listJosephus = new Josephus();
        String names [] = {"Ricky","Pablo", "Mario","Ro","Ki"};
        String [] actual = listJosephus.order(names, 2);
        for(int i = 0;i < actual.length  ; i++ ) {
            if ( i  == actual.length - 1){
                System.out.println("The winner is " + actual[actual.length -1]);
            }
            else
            System.out.println(actual[i] + " is out ");
        }
        
        assertEquals(actual[0],"Pablo");
        assertEquals(actual[1],"Ro");
        assertEquals(actual[2],"Ricky");
        assertEquals(actual[3],"Ki");
	}
	
	@Test
	public void testOrder2() {
		Josephus listJosephus = new Josephus();
        String names2 [] = {"Ricky","Pablo"};
        String [] actual = listJosephus.order(names2, 2);
        for(int i = 0;i < actual.length  ; i++ ) {
            if ( i  == actual.length - 1){
                System.out.println("The winner is " + actual[actual.length -1]);
            }
            else
            System.out.println(actual[i] + " is out ");
        }
        
        assertEquals(actual[0],"Pablo");
        assertEquals(actual[1],"Ricky");
	}
}
