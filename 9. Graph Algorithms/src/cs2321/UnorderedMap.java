package cs2321;

/*
 * @author Adam Fenjiro
 * CS2321
 * 4/11/2023
 * 
 * This is an implementation of an Unordered Map
 */

import java.util.Iterator;
import net.datastructures.Entry;
import net.datastructures.Map;

//This class will use an ArrayList to store the data 
public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	//Storage for map entries 
	 private ArrayList<mapEntry<K,V>> table = new ArrayList<>(); 

	//Empty map 
	public UnorderedMap() {
	}
	
	//Utility method that returns the index of an entry with equal key, or -1 if none found
	private int findIndex(K key) {
		int n = table.size();
		for(int j =0; j < n; j++) {
			if(table.get(j).getKey().equals(key))return j;
		}
		return -1;
	}
		
	//This method returns the size
	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		return table.size() == 0;
	}

	//This method returns the value associated with the specified key (or else null)
	@Override
    @TimeComplexity("O(n)")
	//This method runs at O(n) because it needs to search if the key exists 
	public V get(K key) {
		int j = findIndex(key);
		if(j == -1) return null;
		return table.get(j).getValue();
	}
	
	
	//This method adds a value at the end of the array, or else override existing value 
	@Override
    @TimeComplexity("O(n)")
	public V put(K key, V value) {
		int j = findIndex(key);
		if(j == -1) {
			table.addLast(new mapEntry<>(key, value));
			return null;
		}
		V old = table.get(j).getValue();	
		table.get(j).setValue(value);
		return old;	
	}

	@Override
	//This method removes value with the specified key and return value or else null
    @TimeComplexity("O(n)")
	public V remove(K key) {
		int j = findIndex(key);
		int n = size();
		if(j == -1) return null;
		V answer = table.get(j).getValue(); 
		if(j != n - 1)
		table.set(j, table.get(n - 1));	    
		table.remove(n - 1);	
		return answer;
	}
	
	
	
	//Hleper method for public entrySet method
	private class EntryIterator implements Iterator<Entry<K,V>>{
		private int j = 0;
		public boolean hasNext() {
			return j < table.size();
		}
		public Entry<K,V> next(){
			if (j == table.size()) System.out.println("Not such element exist");
			return table.get(j++);
		}
	}
	
	private class EntryIterable implements Iterable<Entry<K,V>>{
		public Iterator<Entry<K,V>> iterator(){
			return new EntryIterator();
		}
	}

	//This method returns an iterable collection of all key-value
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		//Hint: Java does not allow the casting from DoublyLinkedList<mapEntry<K,V>> to DoublyLinkedList<Entry<K,V>>
		//Therefore, you can create a new variable with the type DoublyLinkedList<Entry<K,V>>, then add all the data in table to it.
		return new EntryIterable();
	}

}