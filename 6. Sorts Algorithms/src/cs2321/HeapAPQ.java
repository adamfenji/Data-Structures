package cs2321;

import java.util.Comparator;
import net.datastructures.*;


/**
 * Implementation of an Adaptable PriorityQueue based on an heap.
 * CS2321
 * @author Adam Fenjiro
 */


public class HeapAPQ<K,V> implements AdaptablePriorityQueue<K,V> {
	//Creating the heap data:
    ArrayList<apqEntry<K,V>> data = new ArrayList<>();

  //This implements the comparator to compare the values
    private Comparator<K> comp;

  //This calculates the index of the parent node
    @TimeComplexity("O(1)")
    protected int parent(int j) {
        return (j - 1)/2;
    }
  //This calculates the index of the left child node
    @TimeComplexity("O(1)")
    protected int left(int j) {
        return 2 * j + 1;
    }
  //This calculates the index of the right child node
    @TimeComplexity("O(1)")
    protected int right(int j) {
        return 2 * j + 2;}
  //This returns True if is has a left child node
    @TimeComplexity("O(1)")
    protected boolean hasLeft(int j) {
        return left(j) < data.size();
    }
  //This returns True if is has a right child node
    @TimeComplexity("O(1)")
    protected boolean hasRight(int j) {
        return right(j) < data.size();
    }


  //This method validates an entry to ensure the component is in the tree 
    protected apqEntry<K,V> validate(Entry<K,V> entry)throws IllegalArgumentException{
        if(!(entry instanceof apqEntry))
            throw new IllegalArgumentException("Invalid entry");
        apqEntry<K,V> locator = (apqEntry<K,V>) entry;
        int j = locator.getIndex();
        if (j >= data.size() || data.get(j)!= locator)
            throw new IllegalArgumentException("Invalid Entry");
        return locator;
    }


    //This method determines whether the key is valid or not
    protected boolean checkKey(K key)throws IllegalArgumentException{
        try {
            return(comp.compare(key, key) == 0); //see if key can be compared to itself
        }catch(ClassCastException e) {
            throw new IllegalArgumentException("Incompatible Key");
        }
    }

  //This is a helper class to compare heaps
    public class DefaultComparator<K> implements Comparator<K> {
    	// This compare method simply calls the compareTo method of the argument. 
    	// If the argument is not a Comparable object, and therefore there is no compareTo method,
    	// it will throw ClassCastException. 
        public int compare(K a, K b) throws IllegalArgumentException {
            if (a instanceof Comparable ) {
                return ((Comparable <K>) a).compareTo(b);
            } else {
                throw new  IllegalArgumentException();
            }
        }
    }

  //This is a method for comparing two entries according to a key
    public int compare(Entry<K,V> a, Entry<K,V>b) {
        return comp.compare(a.getKey(), b.getKey());
    }


    //Nested Array priority queue class
    //This class will allow us to return key, value and index as a single object
    public static class apqEntry<K,V> implements Entry<K,V> {
        private K k;
        private V v;
        private int index;

        public apqEntry (K key,V value, int i){
            k = key;
            v = value;
            index = i;
        }

      //Getters for key, value, index
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
      //Setters for key, value, index
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
    //default size is 16
    public HeapAPQ() {
        data = new ArrayList<apqEntry<K,V>>();
        comp = new DefaultComparator<K>();
    }

    /* Start the PQ with specified initial capacity */
    public HeapAPQ(int capacity) {
        data = new ArrayList<apqEntry<K,V>>(capacity);
        comp = new DefaultComparator<K>();
    }


    /* Use specified comparator */
    public HeapAPQ(Comparator<K> c) {
        data = new ArrayList<apqEntry<K,V>>();
        this.comp = c;

    }

    /* Use specified comparator and the specified initial capacity */
    public HeapAPQ(Comparator<K> c, int capacity) {
        data = new ArrayList<apqEntry<K,V>>(capacity);
        this.comp = c;
    }

    /*
     * This method is purely for testing purpose of auto-grader
     */
    public Object[] data() {
        Object arr[] = new Object [data.size()];
        for(int i = 0; i < data.size(); i++) {
            arr[i] = data.get(i);
        }
        return arr;
    }

  //This Method returns the number of items in the PQ:
    @Override
    @TimeComplexity("O(1)")
    public int size() {
        return data.size();
    }

  //This method returns True if it is empty
    @TimeComplexity("O(1)")
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * TCJ: o(1) because it only uses getters and setters that are O(1)s
     */
    //This method will swap the entries at indices i and j of the array list
    @TimeComplexity("O(1)")
    protected void swap(int first, int second) {
        Entry<K,V> temp = data.get(first); 		 
        data.set(first, data.get(second));		
        data.set(second, (apqEntry<K, V>) temp); 
        data.get(first).setIndex(first);		 
        data.get(second).setIndex(second);		 
    }
    
    /**
     * TCJ: o(log n) because it is dividing the possibilities in half in each comparision
     */
    //This method moves the entry at index j higher to restore the heap property
    @TimeComplexity("O(log n)")
    @TimeComplexityAmortized("O(1)")
    protected void upHeap(int j) {
        while(j > 0) {
            int parent = parent(j);
            if (comp.compare((K)data.get(j).getKey(),(K)data.get(parent).getKey()) >= 0) break;
            swap(j,parent);
            j = parent;
        }
    }

    /**
     * TCJ: O(log n) because the heap runs the height at O(log n)
     */
    //This method will make sure that the value passed to it go to the right location in the tree
    @TimeComplexity("O(log n)")
    @TimeComplexityAmortized("O(1)")
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

    /**
     * TCJ: O(n) because sometimes the array need to duplicate its size
     */
    //This method inserts a new node to the heap
    @Override
    @TimeComplexity("O(n)")
    @TimeComplexityAmortized("O(log n)")
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        apqEntry<K,V> newEntry = new apqEntry<>(key, value, data.size()); 
        data.addLast(newEntry); 										
        upHeap(data.size() -1); 										 
        return newEntry;
    }
    
  //This method will return the min value
    @TimeComplexity("O(1)")
    @Override
    public Entry<K, V> min() {
        if (data.isEmpty()) return null;
        return data.get(0);
    }


  //This method will remove and return the root node
    @TimeComplexity("O(log n)")
    @TimeComplexityAmortized("O(1)")
    @Override
    public Entry<K, V> removeMin() {
        if(data.isEmpty()) return null; 
        Entry<K,V> answer = data.get(0);
        swap(0,data.size() - 1);	
        data.removeLast();			
        downHeap(0);					
        return answer;					
    }


    /**
     * TCJ: o(log n) because it uses upHeat() and downHeap() that both have O(log n)
     */
	//This method will move the values up until it get to the desire location 
    //With min heap the smallest values will go to the top and with max heap the largest values will go up
    //The longest the value can travel is the height of the tree,
    @TimeComplexity("O(log n)")
    protected void bubble(int j) {
        if(j > 0 && compare(data.get(j),data.get(parent(j))) < 0)
            upHeap(j);
        else
            downHeap(j);
    }

    /**
     * TCJ: O(log n) since after swapping elemetns we use downHeap() that has O(log n)
     */
	@Override
    //This method will remove any value of the tree
    //The value that we want to remove will be changed with the last value added 
    @TimeComplexity("O(log n)")
    @TimeComplexityAmortized("O(1)")
    public void remove(Entry<K, V> entry) throws IllegalArgumentException {
        apqEntry<K,V> locator = validate(entry); 
        int j = locator.getIndex( );			
        swap(j,data.size() - 1);			
        data.removeLast();						
        downHeap(j);							
    }
    /**
     * TCJ
     * It runs O(log(n)) it will find the value and
     * then it will swap the value with the last value added and remove it swap is O(1)
     * in case it is the last value then it will run O(1)
     * After swapping we need to put downHeap which is O(log(n))*/



    @Override
  //This method will replace the key of a heap.
    @TimeComplexity("O(log n)")
    @TimeComplexityAmortized("O(1)")
    public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
        apqEntry<K,V> locator = validate(entry);
        checkKey(key); 		
        locator.setKey(key);	
        data.get(locator.index).setKey(key);
        bubble(locator.getIndex());
    }


    @Override
  //This method will replace the value of the heap into the the entry of the value that we want to replace
    @TimeComplexity("O(1)")
    public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
        apqEntry<K,V> locator = validate(entry); 
        locator.setValue(value);		
    }
    public static void main(String [] args) {
        int arr[][] = {{2,2},{3,3}, {4,4},{5,5}};
        System.out.println(arr.length);
        int array[][] = new int [5][5];

    }


}


