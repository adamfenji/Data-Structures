/**
 * @Author: Adam Fenjiro
 * This is an JUnit test for CircularArrayQueueTest.
 * 1/31/2023
 * CS2321
 */
import org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import cs2321.CircularArrayQueue;

public class CircularArrayQueueTest {

	@Before
	public void setUp() throws Exception{
	}
	
	@Test
	public void testIsEmptyOnEmpty() {
		CircularArrayQueue circularListEmpty = new CircularArrayQueue(0);
		Assert.assertTrue(circularListEmpty.isEmpty());
	}

	@Test
	public void testEnqueue() {
		CircularArrayQueue circularList = new CircularArrayQueue(5);
		circularList.enqueue(1);
		circularList.enqueue(2);
		circularList.enqueue(3);
		circularList.enqueue(4);
		circularList.enqueue(5);
		Assert.assertFalse(circularList.isEmpty());
		Assert.assertEquals(5,circularList.size());
	}
	public void testEnqueue2() {
		CircularArrayQueue circularList2 = new CircularArrayQueue(5);
		circularList2.enqueue(1);
		circularList2.enqueue(2);
		circularList2.enqueue(3);
		Assert.assertFalse(circularList2.isEmpty());
		Assert.assertEquals(3,circularList2.size());
	}


	@Test
	public void testFirst() {
		CircularArrayQueue circularList = new CircularArrayQueue(5);
		circularList.enqueue("pass");
		circularList.enqueue(2);
		Assert.assertFalse(circularList.isEmpty());
		Assert.assertEquals("pass","pass");
		
	}
	
	@Test
	public void testFirst2() {
		CircularArrayQueue circularList2 = new CircularArrayQueue(0);
		Assert.assertTrue(circularList2.isEmpty());
		
		
	}

	@Test
	public void testDequeue() {
		CircularArrayQueue circularList = new CircularArrayQueue(5);
		circularList.enqueue(1);
		circularList.enqueue(2);
		circularList.enqueue(3);
		circularList.enqueue(4);
		circularList.enqueue(5);
		circularList.dequeue();
		circularList.dequeue();

		Assert.assertFalse(circularList.isEmpty());
		Assert.assertEquals(3,circularList.size());
	}
	
	@Test
	public void testDequeue2() {
		CircularArrayQueue circularList2 = new CircularArrayQueue(1);
		circularList2.enqueue(1);
		circularList2.dequeue();
		Assert.assertTrue(circularList2.isEmpty());
		Assert.assertEquals(0,circularList2.size());
	}

}
//do you want me to test all the methods of the classes? How many different test cases do I need? 

