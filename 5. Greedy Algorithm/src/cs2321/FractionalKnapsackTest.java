package cs2321;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit Test of Fractional Knapsack.
 * CS2321
 * @author Adam Fenjiro
 */

public class FractionalKnapsackTest {

	@Before
	public void setUp() throws Exception {
	}
	/**
	 * FractionalKnapsack Case 1:
	 * Input: {{4,12},{8,32},{2,40},{6,30},{1,50}}
	 * Output: 124
	 */

	@Test
	public void testMaximumValue() {
		FractionalKnapsack array = new FractionalKnapsack();
		int list[][] = new int [5][5];
		list[0][0] = 4;
		list[1][0] = 8;
		list[2][0] = 2;
		list[3][0] = 6;
		list[4][0] = 1;
		list[0][1] = 12;
		list[1][1] = 32;
		list[2][1] = 40;
		list[3][1] = 30;
		list[4][1] = 50;
			double expected = 124.0;
			assertEquals(expected,array.MaximumValue(list, 10),0.1); 
	}
	
	/**
	 * FractionalKnapsack Case 2:
	 * Input: {0,0}
	 * Output: 0
	 */
	@Test 
	public void testMaximumValueEmpty() {
		FractionalKnapsack array = new FractionalKnapsack();
		int list[][] = new int [0][0];
			double expected = 0.0;
			assertEquals(expected,array.MaximumValue(list, 10),0.01 );
	}
	/**
	 * FractionalKnapsack Case 3:
	 * Input: {{3,28},{5,7}}
	 * Output: 30.8
	 */
	@Test 
	public void testMaximumValueDouble() {
		FractionalKnapsack array = new FractionalKnapsack();
		int list[][] = new int [2][2];
		list[0][0] = 3;
		list[1][0] = 5;
		list[0][1] = 28;
		list[1][1] = 7;
			double expected = 30.8;
			assertEquals(expected,array.MaximumValue(list, 5), 0.0001);
	
	}
}
