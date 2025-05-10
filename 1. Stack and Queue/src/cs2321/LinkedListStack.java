
/**
 * @Author: Adam Fenjiro
 * This is an implementation of a Stack using a LinkedList.
 * 1/31/2023
 * CS2321
 */

package cs2321;
import net.datastructures.Stack;


public class LinkedListStack<E> implements Stack<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	public LinkedListStack() {};
	

		@Override
		//This returns the size
	public int size() {
		return list.size();
	}

	@Override
	//This returns true if list has 0 values 
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	//This adds element e to the top of the stack
	public void push(E element) {
		list.addFirst(element);
	}

	@Override
	//This returns the top element o the stack, without removing it 
	public E top() {
		return list.first();
	}

	@Override
	//This removes and returns the top element from the stack
	public E pop() {
		return list.removeFirst();
	}
	
	
	public void printStack() {
		list.printList();
	}



}
