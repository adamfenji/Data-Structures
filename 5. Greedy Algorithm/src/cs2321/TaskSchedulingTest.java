package cs2321;

import static org.junit.Assert.*;

/**
 * Unit Test of Fractional Knapsack.
 * CS2321
 * @author Adam Fenjiro
 */

import org.junit.Before;
import org.junit.Test;

public class TaskSchedulingTest {

	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * TaskScheduling Case 1:
	 * Input: [1,4][1,3][2,5][3,3][6,9][7,8]
	 * Output:3
	 */

	@Test
	public void testNumOfMachines() {
		   TaskScheduling array = new TaskScheduling();
		   int list[][] = new int [6][6];
			list[0][0] = 1;
			list[1][0] = 1;
			list[2][0] = 2;
			list[3][0] = 3;
			list[4][0] = 6;
			list[5][0] = 7;
			list[0][1] = 4;
			list[1][1] = 3;
			list[2][1] = 5;
			list[3][1] = 3;
			list[4][1] = 9;
			list[5][1] = 8;
			int expected = 3;
			assertEquals(expected,array.NumOfMachines(list)); 
	}
	
	/**
	 * TaskScheduling Case 1:
	 * Input: [0,0]
	 * Output:0
	 */
	@Test
	public void testNumOfMachinesEmpty() {
		   TaskScheduling array = new TaskScheduling();
		   int list[][] = new int [0][0];
			int expected = 0;
			assertEquals(expected,array.NumOfMachines(list)); 
	}
	
	/**
	 * TaskScheduling Case 1:
	 * Input: [1,4][4,6][6,9]
	 * Output:1
	 */
	@Test
	public void testNumOfMachines2() {
		   TaskScheduling array = new TaskScheduling();
		   int list[][] = new int [3][3];
			list[0][0] = 1;
			list[1][0] = 4;
			list[2][0] = 6;
		
			list[0][1] = 4;
			list[1][1] = 6;
			list[2][1] = 9;
			int expected = 1;
			assertEquals(expected,array.NumOfMachines(list)); 
	}
	
	

}