
import cs2321.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.Position;
import net.datastructures.PositionalList;

public class CustomerTest {
	Customers  cache = new Customers(4);
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	void check (String visitlist, String expected) {
		String[] visitlistArray= visitlist.split(" ");
		String[] expectedArray= expected.split(" ");
		
		PositionalList<String> actual;
		for (String p : visitlistArray) {
			cache.visit(p);
		}
		
		actual = cache.getList();
		
		Object[] actualArray = actual.toArray();

		assertArrayEquals(expectedArray, actualArray);
	}

	/* one element*/
	@Test
	public void testGetList1() {
		check("A", "A");
	}
	
	/* two elements in correct order*/
	@Test
	public void testGetList2() {
		check("A B ", "B A");
	}
	
	/* three elements in correct order*/
	@Test
	public void testGetList3() {;
		check("A B C ", "C B A");
	}
	
	/* the data is in the cache already, put at the beginning of the least */
	@Test
	public void testGetList4() {
		check("A B C B", "B C A");
	
	}
	
	/* Kick the least recent used data, put the new at the beginning */
	
	@Test
	public void testGetList6() {
		check("A B C D E" , "E D C B");
	}
	
	
	/* a long list of visits and cache replacements */
	@Test
	public void testGetList7() {
		check("A B C D E D F", "F D E C");
	}
	
	/* a long list of visits and cache replacements continuously */
	@Test
	public void testGetList8() {
		check("A B C D E D F", "F D E C");
		check("E", "E F D C");
		check("A", "A E F D");
	}
	
	/* a long list of visits and cache replacements continuously */
	@Test
	public void testGetList9() {
		cache.visit("A");
		cache.visit("B");
		cache.resetList();
		assertEquals(cache.getList().size() , 0);
		cache.visit("A");
		assertEquals(cache.getList().size() , 1);
	}
	

}
