package cs2321;

/**
 * Author: Adam Fenjiro
 * CS2321
 * 4/2/2023
 * 
 * This is an implementation of an ArrayList
 */

import java.util.Iterator; 
import net.datastructures.List;

public class ArrayList<E> implements List<E> {
	
	//Instance variables
	private E[] data;
	private int size = 0;
	
	//Constructs a list with 16 of capacity 
	public ArrayList() {
		this(16);
	}
	
	//Constructs a list with the given capacity
	public ArrayList(int capacity) {
		data = (E[])new Object[capacity];
	}
	
	@Override
	public int size() {
		return size;
	}

	//Returns the current capacity of array, not the number of elements.
	public int capacity() {
		return data.length;
	}
	
	
	@Override
	//Returns whether the array list is empty 
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	//Returns but does not remove the element at index i
	public E get(int index) throws IndexOutOfBoundsException {
		checkIndex(index,size);
		return data[index];
		}

	@Override
	//Replaces the element at index i with e, and returns the replaced element 
	public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index,size);
		E temp = data[index];
		data[index] = element;
		return temp;
	}
	
	//This method duplicates the capacity of the array
    public void expandCapacity(){
        E[] temp = (E[]) new Object[data.length * 2];
        for(int k = 0; k < data.length; k++) {
            temp[k] = data[k];
            }			
        data = temp;   
    }
    
	@Override
	//This inserts element e to be at index i, shifting all subsequent elements later
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if (size == data.length) {				
			expandCapacity();
			} 
		for(int k = size - 1; k >= i; k--) {							 
			data[k+1] = data[k];}
		data[i] = e;												 
		size++;	
	}

	//This method will add an element at the beginning of the arrayList
	public void addFirst(E e)  {
	add(0,e);
	}
	
	//This method uses the add method to add at an element at the end of the list
	public void addLast(E e)  {
		add(size,e);
	}
		
	//This removes and returns the element at index i, shifting subsequent elements earlier
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];										
		for(int k = i; k < size - 1; k++)						
			data[k] = data[k + 1];
		data[size - 1] = null;									
		size--;
		return temp;	
	}
	//This removes and returns the first value 
	public E removeFirst() throws IndexOutOfBoundsException {
		if (size == data.length) 
			throw new IndexOutOfBoundsException("Array is full");
		E temp = data[0];
		for(int k = 0; k < data.length - 1; k++) {
			data[k] = data[k + 1];
		}
		data[data.length - 1] = null;
		size--;
		return temp;
	}
	//This removes and returns the last element
	public E removeLast() throws IndexOutOfBoundsException {
		E temp = data[size - 1];
		data[size - 1] = null;
		size--;
		return temp;
	}
	
	//Helper method to print the list 
	 public void printList() {
	        if (isEmpty()) {
	            System.out.println("EMPTY");
	            return;
	        }
	        for(int i = 0;i < data.length; i++ ) {
	            System.out.println(data[i]);
	        }
	    }
	 
	 //utility method
	/**Checks whether the given index is in the range [0, n-1]*/
	protected void checkIndex(int i, int n)throws IndexOutOfBoundsException{
		if (i < 0|| i >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + i);
	}
	 
	@Override
	//This returns an iterator of the elements stored in the list
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
	

	//Nested class to create iterator 
	//This is used to traverse each and every element in the collection
	private class ArrayListIterator implements Iterator<E> {
		private int j = 0;				 
		private boolean removable = false; 
	
		@Override
		public boolean hasNext() {
			return j < size;
		}

		@Override
		public E next() {
			if (j == size)
				removable = true;
			return data[j++];     
		}
		
	}
}
