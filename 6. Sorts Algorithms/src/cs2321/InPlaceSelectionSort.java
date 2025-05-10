package cs2321;

/**
 * Implementation of an InPlaceSelectionSort.
 * CS2321
 * @author Adam Fenjiro
 */

public class InPlaceSelectionSort<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 * 			K is an generic, but comparable type. 
	 *          Don't cast K to int type . Don't use == to compare keys, use compareTo method.
	 */

	@TimeComplexity("O(n^2)")
    @TimeComplexityAmortized("O(n^2)")
	public void sort(E[] array) {
		for(int i = 0; i < array.length; i++) {
			int minIndex = i;
			for(int j = i; j < array.length; j++) {
				if(array[j].compareTo(array[minIndex]) < 0) {
					minIndex = j;
				}
			}
			E temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}

}
