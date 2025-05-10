package cs2321;

/**
 * Implementation of a HeapPQSort.
 * CS2321
 * @author Adam Fenjiro
 */

public class HeapPQSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an PrioiryQueue Sort using the heap implementation of PQ.
	 * @param array - Array to sort
	 * 			K is an generic, but comparable type. 
	 * 			Don't cast K to int type . Don't use == to compare keys, use compareTo method.
	 */
	
	HeapAPQ data = new HeapAPQ();
	
	@TimeComplexity("O(n^2)")
    @TimeComplexityAmortized("O(n^2)")
	public void sort(E[] array) {
		for(int i = 0; i < array.length; i++) {
			data.insert(array[i],null);
		}
		for(int i = 0; i < array.length; i++) {
			array[i] = (E) data.removeMin().getKey(); //This returns the values to the array. (In order)
		}
	} 

}
