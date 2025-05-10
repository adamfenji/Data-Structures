/**
 * @Author: Adam Fenjiro
 * This is an JUnit test for LinkedListStack.
 * 1/31/2023
 * CS2321
 */
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import cs2321.LinkedListStack;

public class LinkedListStackTest {

	@Test
	public void testIsEmpty() {
		LinkedListStack val = new LinkedListStack();
		Assert.assertTrue(val.isEmpty());

	}
	

	@Test
	public void testPush() {
		LinkedListStack val = new LinkedListStack();
		val.push(1);
        assertEquals(val.pop(),1);

	}
	
	@Test
	public void testPush2() {
		LinkedListStack val = new LinkedListStack();
		val.push(1);
		val.push(4);
        assertEquals(val.top(),4);

	}

	@Test
	public void testTop() {
		LinkedListStack val = new LinkedListStack();
		val.push(1);
		val.push(2);
		val.push(4);
		val.push(2);
		val.push(9);
        assertEquals(val.top(),9);

	}

	@Test
	public void testPop() {
		LinkedListStack val = new LinkedListStack();
		val.push(1);
		val.push(2);
		val.push(4);
		val.pop();
        assertEquals(val.top(),2);
	}
	public void testPop2() {
		LinkedListStack val = new LinkedListStack();
		val.push(1);
		val.push(2);
		val.push(4);
		val.push(9);
		val.push(7);
		val.push(6);
		val.pop();
		val.pop();
        assertEquals(val.top(),9);
	}
	public void testSize() {
		LinkedListStack val = new LinkedListStack();
		val.push(1);
		val.push(2);
		val.push(4);
        assertEquals(val.size(),3);
	}

}
