package cs2321;

/**
 * Implementation of an InPlaceInsertionSort.
 * CS2321
 * @author Adam Fenjiro
 */

public class InPlaceInsertionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 * 	 		K is an generic, but comparable type. 
	 * 			Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
	*/
	@TimeComplexity("O(n^2)")
    @TimeComplexityAmortized("O(n)")
	public void sort(E[] array) {
		for(int i = 1; i < array.length; i++) {
			E current  = array[i]; 
			int j = i - 1;
			while(j >= 0 && array[j].compareTo(current) > 0) {
				array[j + 1] = array[j];  
				j--;					  
			}
			array[j + 1] = current;	
		}
	}

}
