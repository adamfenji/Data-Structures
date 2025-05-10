/**
 * @author Adam Fenjiro
 * CS2321
 * 2/7/2023
 * 
 * This is an implementation of a DoublyLinkedList.
 */

package cs2321;
import net.datastructures.List; 
import net.datastructures.Position; 
import net.datastructures.PositionalList; 
import java.util.Iterator; 


public class DoublyLinkedList<E> implements PositionalList<E> {
	/* 
	 * Node class which should contain element and pointers to previous and next nodes
	 */ 
	public static class Node<E> implements Position<E> {
		private E element;							
		private Node<E> prev;						
		private Node<E> next;						
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		@Override
		public E getElement() {
			if (next == null)
				throw new IllegalStateException("Position no longer valid");
			return element;
		}
		public Node<E> getPrev(){
			return prev;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setElement(E e) {
			element = e;
		}
		public void setPrev(Node<E> p) {
			prev = p;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	//End of nested node class
	
	//Instance variables of the linked positional list 
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;
	private E[] items;
	
	//Construct a new empty list
	public DoublyLinkedList() {
		head = new Node<>(null, null, null); 
		tail = new Node<>(null, head, null); 
		head.setNext(tail);					
	}
	
	//private utilities 
	//validates the position and returns it as a node
	private Node<E> validate(Position<E> p)throws IllegalArgumentException{
		if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p; //safe cast
		if (node.getNext() == null) 
		throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}
	
	
	//Returns the given node as a Position or null, if it is empty
	private Position<E> position(Node<E> node){
		if (node == head || node == tail)
			return null;
		return node;
	}
	
	//Positional List interface methods 	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	//returns the first position in the double linked list or null if it is empty
	public Position<E> first() {
		return position(head.getNext());
	}

	@Override
	//this returns the last position in the linked list
	public Position<E> last() {
		return position(tail.getPrev());
	}
	

	@Override
	//This returns the position immediately before position p
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	@Override
	//This returns the position immediately after position p
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	

	//Private utilities 
	/**Adds element e to the doubly linked list between the given node */
	//Adds element e to the linked list between the given nodes
		private Position<E> addBetween(E e, Node<E> pred, Node<E>succ){
			Node<E> newest = new Node<>(e,pred, succ);
			pred.setNext(newest);
			succ.setPrev(newest);
			size++;
			return newest;
		}

		@Override
		public Position<E> addFirst(E e) {
			return addBetween(e, head, head.getNext());
		}

		@Override
		public Position<E> addLast(E e) {
			return addBetween(e, tail.getPrev(),tail);
		}

		@Override
		public Position<E> addBefore(Position<E> p, E e)throws IllegalArgumentException {
			Node<E> node = validate(p);
			return addBetween(e, node.getPrev(), node);
		}

		@Override
		public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
			Node<E> node = validate(p);
			return addBetween(e, node, node.getNext());
			}

		@Override
		public E set(Position<E> p, E e) throws IllegalArgumentException {
			Node<E> node = validate(p);
			E answer = node.getElement();
		    node.setElement(e);
		    return answer;
		}

		@Override
		//This removes the element stored at position p and returns it
		public E remove(Position<E> p) throws IllegalArgumentException {
			Node<E> node = validate(p);
			Node<E> predecessor = node.getPrev();
			Node<E> successor = node.getNext();
			predecessor.setNext(successor); 
			successor.setPrev(predecessor);
			size--;
			E answer = node.getElement();
			node.setElement(null);		
			node.setNext(null);			
			node.setPrev(null);
			return answer;
		}
	
		
		
			
	//Element iterator will return one element at a time  
	//Nested ElementIterator class
	//This class adapts the iterator produced by positional() to return elements	
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = new PositionIterator();
		
		@Override
		public boolean hasNext() {
			return posIterator.hasNext();
		}

		@Override
		public E next() {
			return posIterator.next().getElement();
		}
		public void remove() {posIterator.remove();
	    }
	}
	
	@Override
	//This returns an iterator of the element stored in the list
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	
	
		//nested PositionIterator class
	/*
	 * Position iterator will return one Position at a time, points to each node 
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first(); //position of the next element to report
		private Position<E> recent  = null;  //position of last reported element
		//Test whether the iterator has a next object 
		@Override
		public boolean hasNext() {
			return(cursor != null);
		}
		//returns the next position in the iterator 
		@Override
		public Position<E> next() {
			if (cursor == null) {System.out.println("nothing left");}
			recent =cursor;					//element at this position might later be removed 
			cursor = after(cursor);
			return recent;
		}
		//This removes the element returned by most recent call to next
		public void remove() throws IllegalStateException{
			if (recent == null)throw new IllegalStateException("nothing to remove");
			DoublyLinkedList.this.remove(recent); 
			recent = null;						
		}
	}
	
	
		
	/*
	 * Position iterator will return one Position at a time 
	 * Nested PostionIterable class 
	 * Returns an iterable representation of the list's positions, is just making an instance of position iterator 
	 */
	
	private class PositionIterable implements Iterable<Position<E>> {
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}
	
		@Override
	public Iterable<Position<E>> positions(){
		return new PositionIterable(); //create a new instance of the inner class
	}

	//remove to for this method 
	public E removeFirst() throws IllegalArgumentException {
		if(isEmpty()) return null;
		return remove(head.getNext());
		
	}
	//remove last element 
	public E removeLast() throws IllegalArgumentException {
		if(isEmpty())return null;
		return remove(tail.getPrev());
	}
	
	//It changes the list to an array 
    @Override
    public Object [] toArray() {	
        if(isEmpty())return null;				
        E[] listArray = (E[]) new Object[size];		
        Node<E> current = this.head;				
        for(int n = 0;n < size;n++){				
            listArray[n] = current.next.getElement();
            current = current.next;
        }
        return listArray;
    }

    //This will find if an element is already stored in the list
    public Node<E> find(E element) {
    	Node<E> current = this.head; 				    
    	while(current.getNext() != null) { 			    
    		if(element.equals(current.getElement())) { 
    			return current; 					    
    		}
    		current = current.getNext();			    
    	}
    	return null;								   
    }

}

