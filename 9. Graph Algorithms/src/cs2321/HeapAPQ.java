package cs2321;

import java.util.Comparator;
import net.datastructures.*;

/**
 * @author Adam Fenjiro
 * CS2321
 * 04/16/2023
 * A Adaptable PriorityQueue based on an heap.
 */


public class HeapAPQ<K,V> implements AdaptablePriorityQueue<K,V> {
    //This creates the heap 
    ArrayList<apqEntry<K,V>> data = new ArrayList<>();
    
    private Comparator<K> comp;

    //This method returns the index of the parent 
    protected int parent(int j) {
        return (j - 1)/2;
    }
    //This method returns the index of the left Children 
    protected int left(int j) {
        return 2 * j + 1;
    }
    //This method returns the index of the right children
    protected int right(int j) {
        return 2 * j + 2;}
    //This method returns True if is has a left children 
    protected boolean hasLeft(int j) {
        return left(j) < data.size();
    }
    //This method returns True if it has a right children
    protected boolean hasRight(int j) {
        return right(j) < data.size();
    }


    //This method validates an entry to ensure the component in the tree 
    protected apqEntry<K,V> validate(Entry<K,V> entry)throws IllegalArgumentException{
        if(!(entry instanceof apqEntry))
            throw new IllegalArgumentException("Invalid entry");
        apqEntry<K,V> locator = (apqEntry<K,V>) entry;
        int j = locator.getIndex();
        if (j >= data.size() || data.get(j)!= locator)
            throw new IllegalArgumentException("Invalid Entry");
        return locator;
    }
    

    //This method determines whether the key is valid
    protected boolean checkKey(K key)throws IllegalArgumentException{
        try {
            return(comp.compare(key, key) == 0);
        }catch(ClassCastException e) {
            throw new IllegalArgumentException("Incompatible Key");
        }
    }

    //helper method:
    public class DefaultComparator<K> implements Comparator<K> {
        public int compare(K a, K b) throws IllegalArgumentException {
            if (a instanceof Comparable ) {
                return ((Comparable <K>) a).compareTo(b);
            } else {
                throw new  IllegalArgumentException();
            }
        }
    }

    //This method is for comparing two entries according to key
    public int compare(Entry<K,V> a, Entry<K,V>b) {
        return comp.compare(a.getKey(), b.getKey());
    }


    //Nested Array priority queue class
    public static class apqEntry<K,V> implements Entry<K,V> {
        private K k;
        private V v;
        private int index;

        public apqEntry (K key,V value, int i){
            k = key;
            v = value;
            index = i;
        }

        @Override
        public K getKey() {
            return k;
        }
        @Override
        public V getValue() {
            return v;
        }
        public int getIndex() {
        	return index;
        }
        public K setKey(K key) {
            return k = key;
        }
        public int setIndex(int i) {
        	return index = i;
        }
        protected V setValue(V value) {
            return v = value;
        }

    }


    /* If no comparator is provided, use the default comparator.
     * See the inner class DefaultComparator above.
     * If no initial capacity is specified, use the default initial capacity.
     */
    //default size equals 16 
    public HeapAPQ() {
        data = new ArrayList<apqEntry<K,V>>();
        comp = new DefaultComparator<K>();
    }

    public HeapAPQ(int capacity) {
        data = new ArrayList<apqEntry<K,V>>(capacity);
        comp = new DefaultComparator<K>();
    }

    public HeapAPQ(Comparator<K> c) {
        data = new ArrayList<apqEntry<K,V>>();
        this.comp = c;

    }

    public HeapAPQ(Comparator<K> c, int capacity) {
        data = new ArrayList<apqEntry<K,V>>(capacity);
        this.comp = c;
    }

    /*
     * This method returns the array in arraylist that is used to store entries
     * This method is purely for testing purpose of auto-grader
     */
    public Object[] data() {
    	Object arr[] = new Object [data.size()];
     for(int i = 0; i < data.size(); i++) {
    	  arr[i] = data.get(i);
     }
    return arr;
    }
    
    //This method returns the number of items in the priority queue
    @Override
    public int size() {
        return data.size();
    }
    
    //This method returns True if it is empty
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    //This Exchanges the entries at indices i and j of the array list
    protected void swap(int first, int second) {
        Entry<K,V> temp = data.get(first); 		 
        data.set(first, data.get(second));		
        data.set(second, (apqEntry<K, V>) temp);
        data.get(first).setIndex(first);		
        data.get(second).setIndex(second);		
    }
    
    
    protected void upHeap(int j) {
        while(j > 0) {
            int parent = parent(j);
            if (comp.compare((K)data.get(j).getKey(),(K)data.get(parent).getKey()) >= 0) break;
            swap(j,parent);
            j = parent;
        }
    }

    protected void downHeap(int j) {
        while(hasLeft(j)) {		
            int leftIndex = left(j);
            int smallChildrenIndex = leftIndex;
            if(hasRight(j)) {
                int rightIndex = right(j);
                if (comp.compare((K)data.get(leftIndex).getKey(), (K)data.get(rightIndex).getKey()) > 0) {
                    smallChildrenIndex = rightIndex;}
            }
            if (comp.compare((K)data.get(smallChildrenIndex).getKey(), (K)data.get(j).getKey()) >= 0)
                break;
            swap(j, smallChildrenIndex);
            j = smallChildrenIndex;
        }
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        apqEntry<K,V> newEntry = new apqEntry<>(key, value, data.size());   
        data.addLast(newEntry); 										 
        upHeap(data.size() -1); 									
        return newEntry; 
    }

    //This will return the minimum value 
    @Override 
    public Entry<K, V> min() {
        if (data.isEmpty()) return null;
        return data.get(0);
    }

    //This will remove the root node
    @Override
    public Entry<K, V> removeMin() {
        if(data.isEmpty()) return null;  
        Entry<K,V> answer = data.get(0);
        swap(0,data.size() - 1);	
        data.removeLast();		
        downHeap(0);				
        return answer;			
    }

    
    //This method will move the values up until it get to the desire location 
    protected void bubble(int j) {
        if(j > 0 && compare(data.get(j),data.get(parent(j))) < 0)
            upHeap(j);
        else
            downHeap(j);
    }

    @Override
    //This method will remove any value of the tree
    public void remove(Entry<K, V> entry) throws IllegalArgumentException {
    	apqEntry<K,V> locator = validate(entry);
        int j = locator.getIndex( );	
        swap(j,data.size() - 1);		
        data.removeLast();			
        downHeap(j);			
    } 

    @Override
    //This method will replace the key of a heap. 
    public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
    	apqEntry<K,V> locator = validate(entry); 	
        checkKey(key); 			
        locator.setKey(key);
        data.get(locator.index).setKey(key); 
        bubble(locator.getIndex());	
    }


    @Override
    //This method will replace the value of the heap 

    public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
    	apqEntry<K,V> locator = validate(entry); 
        locator.setValue(value);			
    } 	
    }

    