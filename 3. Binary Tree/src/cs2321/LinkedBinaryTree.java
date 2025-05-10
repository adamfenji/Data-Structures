/**
 * @Author: Adam Fenjiro
 * This is an implementation of a Linked Binary Tree.
 * 2/14/2023
 * CS2321
 */
package cs2321;
import java.util.Iterator;
import net.datastructures.*;

public class LinkedBinaryTree<E> implements BinaryTree<E>{
	
	//private Node class:
	private static class Node<E> implements Position<E> {
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;

		public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
			this.element = element;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}
		
	}
	//End of the private node class
	
	private Node<E> root;
	private int size;
	
	//Setting the root to be null and the size to be 0.
	public  LinkedBinaryTree( ) {
		root = null;
		size = 0;
	}


	//This method returns the root node
	@Override
	public Position<E> root() {
		return root; 
	}
	
	
	//This method returns the parent node of node.
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	//This method returns the left node of a node.
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	//This method returns the right node of a node.
	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}
	
	//This method checks if the node is internal, returns true if it is.
	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) > 0;
	}

	//This method checks if the node is external, returns true if it is.
	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) == 0;
	}


	//This method returns the size of the tree.
	@Override
	public int size() {
		return size;
	}

	//This method checks if the tree is empty, returns true if it is.
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	//These are two methods I used as helpers:
	//1. This helper method was used to check whether a node is internal or external
	private int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null) count++;
        if (right(p) != null) count++;
        return count;
    }
	//2. This helper method was used to return the parent, right, and left node.
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node) throw new IllegalArgumentException("Invalid position");
        return node;
    }
	
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty()) {
			throw new IllegalStateException("Tree is not empty");
		}
		root = new Node<>(e, null, null, null);
		size = 1;
		return root;
	}
	
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeft() != null) {
			throw new IllegalArgumentException("Node already has a left child");
		}
		Node<E> child = new Node<>(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRight() != null) {
			throw new IllegalArgumentException("Node already has a right child");
		}
		Node<E> child = new Node<>(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
		
	
	/* Set element in p.
	 * @return the old element in p. 
	 */
	public E setElement(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
	    E oldElement = node.getElement();
	    node.setElement(e);
	    return oldElement;
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
	    if (numChildren(node) == 2) {
	        throw new IllegalArgumentException("p has two children");
	    }
	    Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
	    if (child != null) {
	        child.setParent(node.getParent()); // child's grandparent becomes its parent
	    }
	    if (node == root) {
	        root = child; // child becomes new root
	    } else {
	        Node<E> parent = node.getParent();
	        if (node == parent.getLeft()) {
	            parent.setLeft(child);
	        } else {
	            parent.setRight(child);
	        }
	    }
	    size--;
	    E temp = node.getElement();
	    node.setElement(null);
	    node.setLeft(null);
	    node.setRight(null);
	    node.setParent(node);
	    return temp;
	}
	
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the in-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> inOrderElements(Position<E> p) {
		List<E> elements = new ArrayList<>();
        if (!isEmpty()) {
            inOrderTraversal(root, elements);
        }
        return elements;
	}
	//helper method for inOrderElements():
	// Recursively performs inorder traversal of the tree
    private void inOrderTraversal(Position<E> p, List<E> elements) {
        if (left(p) != null) {
            inOrderTraversal(left(p), elements);
        }
        elements.add(0, p.getElement());
        if (right(p) != null) {
            inOrderTraversal(right(p), elements);
        }
    }
	
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the pre-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> preOrderElements(Position<E> p) {
		List<E> elements = new ArrayList<>();
        if (!isEmpty()) {
            preOrderTraversal(root, elements);
        }
        return elements;
	}
	//helper method for preOrderElements():
	// Recursively performs preorder traversal of the tree
    private void preOrderTraversal(Position<E> p, List<E> elements) {
        elements.add(0, p.getElement());
        if (left(p) != null) {
            preOrderTraversal(left(p), elements);
        }
        if (right(p) != null) {
            preOrderTraversal(right(p), elements);
        }
    }
	
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the post-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> postOrderElements(Position<E> p) {
		List<E> elements = new ArrayList<>();
        if (!isEmpty()) {
            postOrderTraversal(root, elements);
        }
        return elements;
	}
	//helper method for postOrderElements():
	// Recursively performs postorder traversal of the tree
    private void postOrderTraversal(Position<E> p, List<E> elements) {
        if (left(p) != null) {
            postOrderTraversal(left(p), elements);
        }
        if (right(p) != null) {
            postOrderTraversal(right(p), elements);
        }
        elements.add(0, p.getElement());
    }
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the level-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> levelOrderElements(Position<E> p) {
		List<E> elements = new ArrayList<>();
        if (!isEmpty()) {
        	CircularArrayQueue<Position<E>> queue = null;
            queue.enqueue(root);
            while (!queue.isEmpty()) {
                Position<E> p1 = queue.dequeue();
                elements.add(0, p1.getElement());
                if (left(p1) != null) {
                    queue.enqueue(left(p1));
                }
                if (right(p1) != null) {
                    queue.enqueue(right(p1));
                }
            }
        }
        return elements;
	}

}
