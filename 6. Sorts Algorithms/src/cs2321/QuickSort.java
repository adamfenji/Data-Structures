package cs2321;

/**
 * Implementation of a QuickSort.
 * CS2321
 * @author Adam Fenjiro
 */

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {
	
	/**
	 * sort - Perform quick sort. - Feel free to create helper methods. 
	 * @param array - Array to sort 
	 * 			E is an generic, but comparable type. 
	 * 			Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
	 */	
	
	@TimeComplexity("O(n^2)")
    @TimeComplexityExpected("O(n log n)")
	public void sort(E[] array) {
		sort(array, 0, array.length - 1);
	}
	
	private void sort(E[] array, int start, int end) {
		if(start >= end) return;
		
		int boundary = partition(array,start, end);
		//Recursion:
		sort(array,start, boundary - 1);
		sort(array,boundary + 1, end);
	}
	
	//This method returns the pivot position, it also moves the values that are smaller than the pivot to the left and larger to the right 
	
	@TimeComplexity("O(n)")
	private <E extends Comparable<E>> int partition(E[] array,int start, int end){ 
		
		E x = array[end]; 
		int i = start;	 
		int j = end - 1;
		
		while(i <= j) {
			
			while(i <= j && array[i].compareTo(x) < 0) {
				i++;	 		
			}
			while(i <= j && array[j].compareTo(x) > 0) {
				j--;	
			}
			if(i <= j) {
				E temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++; j--;
			}
		}
		E temp = array[i];
		array[i] = x;
		array[end] = temp;
		
		return i;
	}
}
