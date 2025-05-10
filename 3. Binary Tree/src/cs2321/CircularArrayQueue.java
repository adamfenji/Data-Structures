/**
 * @Author: Adam Fenjiro
 * This is an implementation of a Queue using a circular Array.
 * 1/31/2023
 * CS2321
 */

package cs2321;
import net.datastructures.Queue;

//This rotates the front element of the queue to the back of the queue 
public class CircularArrayQueue<E> implements Queue<E> {
	private E[] items;
	private int back;
	private int size;
	private int front;
	
	public CircularArrayQueue(int queueSize) {
		items = (E[]) new Object[queueSize];
	}


 
	@Override
	//This will return the size of the queue 
	public int size() {		
		return size;
	}

	@Override
	//Returns true if size equal 0
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	//This will add the value at the beginning
	public void enqueue(E element) throws IllegalStateException {
		if (size == items.length) throw new IllegalStateException("Queue is full");
		items[back] =element;
		back = (back + 1) % items.length;
		size++;
	}
	

	@Override
	public E first() {
		if (isEmpty()) return null;
		return items[front];
	}

	@Override
	//This will delete the first value added
	public E dequeue() {
		E item = items[front];
		items[front] = null;
		front = (front + 1) % items.length;
		size--;
		return item;
	}
	
	//the method is created to print the list 	
	public void printList() {
		if (isEmpty()) {
			System.out.println("EMPTY");
			return;
		}
		for(int i = 0;i < items.length; i++ ) {
			System.out.println(items[i]);
		}
	}
	
	 
}
