/**
 * @Author: Adam Fenjiro
 * This is an implementation of a LinkedList.
 * 1/31/2023
 * CS2321
 */

package cs2321;

public class SinglyLinkedList<E> {
	
	//This creates a node class
	public static class Node<E>{
		private E element;		 //reference to the element stored at this node
		private Node<E> next;	 //reference to the subsequent node in the list 
		public Node(E e, Node<E>n) {
			element = e;
			next = n;
		}
		
	//getters and setters:
	public E getElement() {
		return element;
	}
	public Node<E> getNext(){
		return next;
	}
	public void setNext(Node<E>n) {
		next = n;
	}
	}
	//--End of nested class--

	private Node<E> head = null; 
	private Node<E> tail = null;
	private int size = 0;
	public SinglyLinkedList() {}
	
	//This returns the size of the linked list 
	public int size() {
		return size;
	}
	//returns true if size is equal 0
	public boolean isEmpty() {
		return size == 0;
	}
	//it will return but not remove the first element of the linked list 
	public E first() {						
		if (isEmpty()) return null;
		return head.getElement();
	}
	//it will return but not remove the last element of the singly linked list 
	public E last() {
		if(isEmpty()) return null;
	return tail.getElement();
	}
	
	//add at the beginning of the linked list 
	public void addFirst(E e) {
		head = new Node<>(e, head); //create, and link new node 
		if (size == 0)
			tail = head;			//special case, new node becomes tail also
		size++;
	}
	//This adds at the end of singly linked list 
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null); //node will eventually be the tail 
		if (isEmpty())
			head = newest;
		else 
			tail.setNext(newest);			//new node after existing tail
		tail = newest;						//new node becomes the tail
		size++;
	}
	//This will remove and return the first element 
	public E removeFirst() {
		if(isEmpty()) return null; 		//nothing to remove 
		E answer = head.getElement();
		head = head.getNext();			//will become null if list had only one node
		size--;
		if (size == 0)
			tail = null;
		return answer;
	}
	//this method was for testing, it will print the linked list 
	public void printList() {
		Node current = head;
		while (current != null) {
			System.out.println(current.element);
			current = current.next;
		}
		
	}
}
