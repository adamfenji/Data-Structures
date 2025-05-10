package cs2321;
import java.util.Iterator;
import net.datastructures.*;
	
/**
 * Author: Adam Fenjiro
 * CS2321
 * 4/2/2023
 * 
 * This is an implementation of a Linked Binary Tree
 */

public class LinkedBinaryTree<E> implements BinaryTree<E>{
	public static class Node<E> implements Position<E>{
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		
		//This constructs a node with the given element and neighbors
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}
		
		//Getters and Setters:
		@Override
		public E getElement() {
			return element;
		}
		
		public Node<E> getParent(){
			return parent;
		}
		public Node<E> getLeft(){
			return left;
		}
		public Node<E> getRight(){
			return right;
		}
		
		public void setElement(E e) {
			element = e;
		}
		public void setParent(Node<E> parentNode) {
			parent = parentNode;
		}
		public void setLeft(Node<E> leftChild) {
			left = leftChild;
		}
		public void setRight(Node<E> rightChild) {
			right = rightChild;
		}
	}
		
	//Factory Function to create a new node storing element e
	Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
		return new Node<E>(e, parent, left, right);
	}
	
	
	//Instance variables
	public Node<E> root = null;
	private int size = 0;
	
	
	@Override
	public Position<E> root() {
		return root;
	}
	
	//Empty constructor
	public LinkedBinaryTree( ) {
	}
	
	//This validates the position and returns it as a node
	public Node<E> validate(Position<E> p)throws IllegalArgumentException{
		if(!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>)p;	
		if(node.getParent() == node)		
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}
	
	
	//This returns the position of p's parent or null if p is root
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	//This returns the position of p left child
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	//This returns the position of p right child
	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}
	
	@Override
	//This returns true if position p has at least one child
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) > 0;
	}
	


	@Override
	//This returns true if position p does not have any children 
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) == 0;
	}

	
	@Override
	//This returns the size 
	public int size() {
		return size;
	}

	@Override
	//This returns true is size is 0 
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	//This places the element e at the root of an empty tree and return its new Position
	public Position<E> addRoot(E e) throws IllegalStateException {
		if(!isEmpty()) throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}
	
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if(parent.getLeft() != null) throw new IllegalArgumentException("p already has left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if(parent.getRight() != null) throw new IllegalArgumentException("p already has right child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
	
	
	//This replaces the element at position p with e and return the replaces element 
	public void setElement(Position<E> p, E element) throws IllegalArgumentException {
		Node<E> node = validate(p);
		node.setElement(element);
	}

	

	
	//This adds positions of the subtree rooted at position p to given snapshot
	private void inorderSubtree(Position<E> p, ArrayList<E> snapshot) {
		if(left(p) != null)
			inorderSubtree(left(p),snapshot);
		snapshot.addLast(p.getElement()); 
		if(right(p) != null)
			inorderSubtree(right(p),snapshot);
	}
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the in-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */		
	public List<E> inOrderElements(Position<E> p) {
		ArrayList<E> list = new ArrayList<E>();
		if(!isEmpty())
			inorderSubtree(p,list);
		return list ;
	}
	

	/**
	 * If p has two children, throw IllegalAugumentException. 
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child. 
	 * If p is root node, update the root accordingly. 
	 * @param p who has at most one child. 
	 * @return the element stored at position p if p was removed.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if(left(node) != null && right(node) != null) {throw new IllegalArgumentException("p has two children");}
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if(child != null)
			child.setParent(node.getParent());	//child's grandparent becomes its parent
		if(node == root)
			root = child;
			else {
			Node<E> parent = node.getParent();
			if(node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);	
		}
		size--;
		E temp = node.getElement();
		node.setElement(null); 
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);	
		return temp;
		}

	
	//This returns the number of children in position p 
	public int numChildren(Position<E> p) {
		int count = 0; 
		if(left(p) != null)
			count++;
		if(right(p) != null)
			count++;
			return count;
	}
	
	//This returns the position of p siblings or null if no sibling exists
	public Position<E> sibling(Position<E> p){
		Position<E> parent = parent(p);
		if(parent == null) return null;
		Node<E> node = validate(p);
		if(p == node.getLeft())
			return right(parent);
		else
			return left(parent);
	}
	
	//for testing, This will print the data in the tree in order
	 private void printIn(Node<E> root) {
		    if(root==null)return;
		    printIn(root.getLeft());
		    System.out.print(root.getElement()+",");
		    printIn(root.getRight());
		  }
	  public void printInorder(){
		    printIn(root);
		  }
}
