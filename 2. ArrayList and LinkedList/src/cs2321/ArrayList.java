/**
 * @author Adam Fenjiro
 * CS2321
 * 2/7/2023
 * 
 * This is an implementation of an ArrayList.
 */
package cs2321;
import net.datastructures.List; 
import net.datastructures.Position; 
import net.datastructures.PositionalList; 
import java.util.Iterator; 

public class ArrayList<E> implements List<E> {
	
	//Instance variables:
	private E[] data; 
	private int size = 0;
	
	//Construct a list with 16 of capacity:
	public ArrayList() {
		this(16);
	}
	//Construct a list with x capacity:
	public ArrayList(int capacity) {
		data = (E[])new Object[capacity];
	}
	
	@Override
	public int size() {
		return size;
	}

	// Return the current capacity of array, not the number of elements:
	public int capacity() {
		return data.length;
	}
	
	
	@Override
	//This returns whether the array list is empty or not:
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
	//This replaces the element at index i with e, and returns the replaced element 
	public E set(int index, E element) throws IndexOutOfBoundsException {
		checkIndex(index,size);
		E temp = data[index];
		data[index] = element;
		return temp;
	}
	
	//This will duplicate the capacity of the array
    public void expandCapacity(){
        E[] temp = (E[]) new Object[data.length * 2];
        for(int k = 0; k < data.length; k++) {
            temp[k] = data[k];
        data = temp;
        }
    }
    
	@Override
	//It inserts element e to be at index i, shifting all subsequent elements later
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if (size == data.length) {				
			expandCapacity();
			} 
		for(int k = size - 1; k >= i; k--) {							//start by shifting rightmost 
			data[k+1] = data[k];}
		data[i] = e;												//ready to replace the element 
		size++;	
	}

	//This method will add an element e at the beginning of the arrayList
	public void addFirst(E e)  {
	add(0,e);
	}
	
	//This uses the add method to add at an element e at the end of the list
	public void addLast(E e)  {
		add(size,e);
	}
		
	//Removes/returns the element at index i, shifting subsequent elements earlier
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
	//This removes and return the first value 
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
	//This removes and return the last element
	public E removeLast() throws IndexOutOfBoundsException {
		if (size >= data.length) 
			throw new IndexOutOfBoundsException("Array is full");
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
	//This checks whether the given index is in the range [0, n-1]
	protected void checkIndex(int i, int n)throws IndexOutOfBoundsException{
		if (i < 0|| i >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + i);
	}
	 
	@Override
	//Return an iterator of the elements stored in the list
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
	

	//Nested class to create Iterator 
	//This is used to traverse each and every element in the collection
	private class ArrayListIterator implements Iterator<E> {
		private int j = 0;		 
		private boolean removable = false; 
	
		@Override
		//This tests whether the iterator has a next object
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
	
	
	public static void main(String [] args) {
		ArrayList <Integer> list2 = new ArrayList<>();
		list2.add(0,1);
		list2.add(1,5);
		list2.addFirst(7);
		
		 for(Iterator i = list2.iterator() ;i.hasNext(); ) {
		 System.out.println(i.next()); }
		
		
	}
}
