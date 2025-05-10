

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import cs2321.*;

public class HeapAPQTest2 {

	HeapAPQ<String, String> heappq;
	
	public static class myReverseComp<K> implements Comparator<K> {
		
		// This compare method simply calls the compareTo method of the argument. 
		// If the argument is not a Comparable object, and therefore there is no compareTo method,
		// it will throw ClassCastException. 
		public int compare(K a, K b) throws IllegalArgumentException {
			if (a instanceof Comparable ) {
			   return ((Comparable <K>) a).compareTo(b) * -1 ;
			} else {
				throw new  IllegalArgumentException();
			}
		}
	} 
	
	@Before
	public void setUp() throws Exception {
		heappq = new HeapAPQ<String, String>(new myReverseComp<String>(), 100);
	}
	
	
	
	@Test
	public void test1000entriesReverse() {
		
		// insert "1000", "1001", ... "2000". 
		// Based on the comparator, 2000 is "minimum", then 1999, .... 
		
		for (int i=1000;i<=2000;i++) {
			heappq.insert(i+"", i+"");
		}
		
		
		for (int i=2000;i>=1000;i--) {
			assertEquals(i+"", heappq.removeMin().getKey());
		}
	}

}
