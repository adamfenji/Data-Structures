package cs2321;
import net.datastructures.*;

/*
 * @author Adam Fenjiro
 * CS2321
 * 4/11/2023
 * 
 * This is an implementation of a HashMap
 */

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	
	//Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	private UnorderedMap<K,V>[]  table;
	int 	size;  
	int 	capacity; 
	int     DefaultCapacity = 17; 
	
	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  
	
	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		this.capacity = hashtablesize;
	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
	capacity = 17;
	table = new UnorderedMap[capacity];
	size = 0;
	
	}
	
	
	//This method makes the operation to determine where should the data be stored 
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	
	//returns the length of the Hashmap
	public int tableSize() {
		return table.length;
	}
	
	//returns the size
	@Override
	public int size() {
		return size;
	}

	//This will return true if the hash map is empty 
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	//This method returns specific value associated with the specific key in the hash map 
	@Override
    @TimeComplexityExpected("O(1)")
	public V get(K key) {
		return bucketGet(hashValue(key), key);
	}

	//This method adds data to the hash map 
	@Override
    @TimeComplexityExpected("O(1)")
	public V put(K key, V value) {
		V answer = bucketPut(hashValue(key), key, value);
		if (size > capacity / 1.33)
			resize(2 * capacity);
		return answer;
	}
	
	//This method resizes the hash map if the size of the values is 75% of the size of the hash map
	private void resize(int newCap) {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size);
		for(Entry<K,V> e: entrySet())
			buffer.addLast(e);
		capacity = newCap;
		createTable();
		size = 0;
		for(Entry<K,V> e : buffer)
			put(e.getKey(), e.getValue());
	}

	@Override
    @TimeComplexityExpected("O(1)")
	public V remove(K key) {
		return bucketRemove(hashValue(key),key);
	}

	@Override
	//This method returns an iterable collection of all key-value entries of the map 
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for(int h = 0; h < capacity; h++)
			if(table[h] != null)
				for(Entry<K,V> entry : table[h].entrySet())
					buffer.addLast(entry);
		return buffer;
	}
	
	//Helper methods that creates an empty table inside a specific index of the array having length equal to current capacity
	private void createTable() {
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];
	}
	
	//This method will return the value associated with key k in bucket with hash value h or else null
	private V bucketGet(int h, K k) {
		UnorderedMap<K,V>  bucket = table[h];
		if(bucket == null) return null;
		return bucket.get(k);
	}
	
	//This method removes the entry having key k from bucket with hash value h
	private V bucketRemove(int h, K k) {
		 UnorderedMap<K,V> bucket = table[h];
		 if(bucket == null) return null;
		 int oldSize = bucket.size(); 		
		 V answer = bucket.remove(k); 		
		 size -= (oldSize - bucket.size()); 
		 return answer;
	}
	
	//This method stores data in a specific index 
	private V bucketPut(int h, K k, V v) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null)
			bucket = table[h] = new UnorderedMap<>(); 
		int oldSize = bucket.size();
		V answer = bucket.put(k, v);
		size += (bucket.size() - oldSize); 
		return answer;
	}
}
