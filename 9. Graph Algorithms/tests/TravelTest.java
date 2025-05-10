import cs2321.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;

public class TravelTest {
	Travel T;
	@Before
	public void setUp() throws Exception {
		/*
	
		              A
                    /   \
                   /      \  
		          8         1             
 		         /           \         
		       /              \
		      B --11-- C --1-- D
		  

		 */ 
		String routes[][] = {  {"A","B","8"},
								 {"A","D","1"},
								 {"B","C","11"},
								 {"C","D","1"}};
		
		T = new Travel(routes);
	}


	
	@Test
	public void testDFS1() {
		String[] expected= {"A","B", "C", "D"};
		String[] list = T.DFS("A");
		assertArrayEquals(expected, list);
	}

	@Test
	public void testDFS2() {
		String[] expected= {"D", "A","B", "C" };
		String[] list = T.DFS("D");
		assertArrayEquals(expected, list);
	}
	

	@Test
	public void testDFS3() {
		String[] expected= {"B","A","D","C" };
		String[] list = T.DFS("B");
		assertArrayEquals(expected, list);
	}
	
	
	@Test
	public void testBFS1() {
		String[] expected= {"A","B", "D", "C"};
		String[] list = T.BFS("A");
		assertArrayEquals(expected, list);
	}

	@Test
	public void testBFS2() {
		String[] expected= {"D","A","C", "B"};
		String[] list = T.BFS("D");
		assertArrayEquals(expected, list);
	}
	@Test
	public void testBFS3() {
		String[] expected= {"B","A", "C", "D"};
		String[] list = T.BFS("B");
		assertArrayEquals(expected, list);
	}
	
	@Test
	public void testDijkstra1() {
		String[][] expected= { {"A", "0"},
				               {"D", "1"},
				               {"C", "2"},
				               {"B", "8"}
				             };
		
		String[][] list = T.Dijkstra("A");
		
		assertArrayEquals(expected, list);
	}

	@Test
	public void testDijkstra2() {
		
		String[][] expected1= { {"D", "0"},
				               {"A", "1"},
				               {"C", "1"},
				               {"B", "9"}
				             };
				             
		
		String[][] expected2= { {"D", "0"},
								{"C", "1"},
								{"A", "1"},
								{"B", "9"}
	             			};
		
		String[][] list = T.Dijkstra("D");
		

		boolean comp= Arrays.deepEquals(list, expected1) || Arrays.deepEquals(list, expected2);
		assertTrue(comp);
	}

	
	@Test
	public void testDijkstra3() {
		
		String[][] expected= { {"B", "0"},
				               {"A", "8"},
				               {"D", "9"},
				               {"C", "10"}
				             };
				             
		
		String[][] list = T.Dijkstra("B");
		
		assertArrayEquals(expected, list);

	}

}
