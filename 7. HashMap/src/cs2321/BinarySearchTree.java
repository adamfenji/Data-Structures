package cs2321;

/**
 * Author: Adam Fenjiro
 * CS2321
 * 4/2/2023
 * 
 * This is an implementation of a Binary Search Tree
 */

import net.datastructures.*;


public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V>  {
	
	/* The base data structure is a linked binary tree with the leaf nodes used as sentinel nodes*/
	// LinkedBinaryTree<Entry<K,V>> tree; 
	LinkedBinaryTree<Entry<K,V>> tree;
	int size;
	private Position<Entry<K,V>> root(){
		return tree.root();
	}
	
	/* 
	 * default constructor
	 */
	public BinarySearchTree() {
		tree = new LinkedBinaryTree<Entry<K,V>>();
		tree.addRoot(null);
	}
	
	/* 
	 * Return the tree. The purpose of this method is purely for testing.  
	 */
	public BinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	
	@Override
	//Returns the number of entries in the map
	public int size(){
		return (tree.size() - 1) / 2; 
	}
	

	@Override
	@TimeComplexity("O(n)")
	//This returns the value v associated with key k, if it exists or else returns null
	public V get(K key) {
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if(tree.isExternal(p)) return null;
		return p.getElement().getValue();
	}
	

	@Override
	@TimeComplexity("O(n)")
	//This associates the given value v with the given key k, returning any overridden value
	public V put(K key, V value) {
		Entry<K,V> newEntry = new mapEntry<>(key,value);
		Position<Entry<K,V>> p = treeSearch(tree.root(), key);
		if(tree.isExternal(p)) {
			expandExternal(p,newEntry);
			return null;
		} else {
			V old = p.getElement().getValue();
			tree.setElement(p, newEntry);
			return old;
		}
	}

	@Override
	@TimeComplexity("O(n)")
	//This removes the entry having key k and returns its associated value
	public V remove(K key) {
		Position<Entry<K,V>> p = treeSearch(root(), key);
		if(p.getElement() == null) return null; 
        if(tree.isExternal(p)) {
        	V old  = p.getElement().getValue();
        	tree.remove(p);
        	return old;}
        else {
            V old = p.getElement().getValue();
            if(tree.isInternal(tree.left(p)) && tree.isInternal(tree.right(p))){
                Position<Entry<K,V>> replacement = treeMax(tree.right(p));
                tree.setElement(p, replacement.getElement());
                p = replacement;
               }
            Position<Entry<K,V>> leaf = (tree.isExternal(tree.left(p)))? tree.left(p): tree.right(p);
            tree.remove(leaf);
            tree.remove(p);
            return old;
        }
	}

	@Override
	//This returns an iterable collection containing all the key-values entries in map
	public Iterable<Entry<K, V>> entrySet() {
		return tree.inOrderElements(tree.root());
	}



	@Override
	//Returns true if the BinarySearchTree is empty 
	public boolean isEmpty() {
		return size() == 0;
	}

	//This returns the position in p subtree having given key or else the terminal leaf
		private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key){
			if(tree.isExternal(p))
				return p;
			int comp = key.compareTo(p.getElement().getKey());
			if(comp == 0)
				return p;
			else if (comp < 0)
				return treeSearch(tree.left(p),key);
			else 
				return treeSearch(tree.right(p),key);		
		}
		
		//Returns True if position p is the root of the tree
		public boolean isRoot(Position<Entry<K,V>> p) {
			return p == root();}
		
		
		
		// This is an utility used when inserting a new entry at a leaf of the tree
		private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) {
			tree.setElement(p, entry);
			tree.addLeft(p, null);
			tree.addRight(p, null);
		}
		
		//This returns the position with the min key in subtree rooted at position p
		private Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p){
			Position<Entry<K,V>> walk = p;
			while(tree.isInternal(walk))
				walk = tree.right(walk);
			return tree.parent(walk);
		}
		
		//This returns the position with the maximum key in subtree rooted at position p
		private Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p){
			Position<Entry<K,V>> walk = p;
			while(tree.isInternal(walk))
				walk = tree.left(walk);
			return tree.parent(walk);
		}

}
