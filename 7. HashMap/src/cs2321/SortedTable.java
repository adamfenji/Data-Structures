package cs2321;

/**
 * Author: Adam Fenjiro
 * CS2321
 * 4/2/2023
 * 
 * This is an implementation of a Sorted Table
 */

import net.datastructures.*;
import java.util.Comparator;

public class SortedTable<K extends Comparable<K>, V> extends AbstractMap<K,V>  {
	ArrayList<mapEntry<K,V>> table = new ArrayList<>();
	
	public SortedTable(){
		super();
	}
	
	private Comparator<K> comp;

	//This method will find the index of the key in a specified range or else return where it should be
	private int findIndex(K key, int low, int high) {
		if(high < low) return high + 1; 
		int mid = (low + high)/2;
		int comparator = key.compareTo(table.get(mid).getKey());
		if (comparator == 0) 
			return mid;
		else if(comparator < 0)
			return findIndex(key, low, mid - 1);  
		else 
			return findIndex(key, mid + 1, high); 
	}
		
	//This method will find the index of the key or return where the key should be 
	private int findIndex(K key) {
		return findIndex(key, 0, table.size() - 1);
	}
	
	@Override
	@TimeComplexity("O(log n)")
	//This returns the value associated with the specified key k
	public V get(K key) {
		int j = findIndex(key);
		if(j == size() || key.compareTo(table.get(j).getKey()) != 0) return null; 
		return table.get(j).getValue();
	}

	@Override
    @TimeComplexity("O(n)")
	//This adds the given value to the specified position key or else it returns any overrode value
	//In case the value was updated it runs log n. O(log n) if map has entry with given key
	public V put(K key, V value) {
		int j = findIndex(key); 
		if(j < size() && key.compareTo(table.get(j).getKey()) == 0) { 
			V old = table.get(j).getValue(); 						  
			table.get(j).setValue(value);						
		return old;													
		}
		table.add(j, new mapEntry<K,V>(key,value));					
		return null;
	}

	@Override
	@TimeComplexity("O(n)")
	//This removes the entry and returns its associated value
	public V remove(K key) {
		int j = findIndex(key);
		if(j == size() || key.compareTo( table.get(j).getKey()) != 0) return null;
		return table.remove(j).getValue();
	}
	
	//Utility Method that returns the entry at index j, or else null if j is out of bounds
	private Entry<K,V> safeEntry(int j){
		if(j < 0 || j >= table.size()) return null;
		return table.get(j);
	}
		
	//This returns the entry with the smallest key value
	public Entry<K,V> firstEntry(){
		return safeEntry(0);
	}
	//This returns the entry with the highest key value 
	public Entry<K, V> lastEntry(){
		return safeEntry(table.size() - 1);
	}
	//This returns the entry with the least key value greater than or equal to k
	public Entry<K, V> ceilingEntry(K key){
		return safeEntry(findIndex(key));
	}
	//This returns the entry with greatest key less than or equal to given key
	public Entry<K,V> floorEntry(K key){
		int j = findIndex(key);
		if(j == size() || ! key.equals(table.get(j).getKey()))
			j--;
		return safeEntry(j);
	}
	//This returns the entry with greatest key strictly less than given key
	public Entry<K,V> lowerEntry(K key){
		return safeEntry(findIndex(key) - 1); 
	}
	
	//This returns the entry with least key strictly than the given key
	public Entry<K, V> higherEntry(K key){
		int j = findIndex(key);
		if(j < size() && key.equals(table.get(j).getKey()))
			j++; 
		return safeEntry(j);
	}

	//helper method for snapshot iterators for entrySet() and subMap()
	private Iterable<Entry<K,V>> snapshot(int startIndex, K stop){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		int j = startIndex;
		while(j < table.size() && (stop == null || stop.compareTo((K)table.get(j)) > 0))
			buffer.addLast(table.get(j++)); 
		return buffer;
	}


	@Override
	//This returns an iterable collection 
	public Iterable<Entry<K, V>> entrySet() {
		return snapshot(0, null);
	}


	@Override
	//This returns the elements in the table
	public int size() {
		return table.size();
	}


	@Override
	//This returns true if it is empty 
	public boolean isEmpty() {
		return table.size() == 0;
	}


}
