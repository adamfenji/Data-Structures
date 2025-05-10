package cs2321;

/**
 * Implementation of a MergeSort.
 * CS2321
 * @author Adam Fenjiro
 */

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {
	/**
	 * sort - Perform merge sort. -Feel free to create other methods. 
	 * @param array - Array to sort
	 * 			 K is an generic, but comparable type. 
	 * 			Don't cast K to int type . Don't use == to compare keys, use compareTo method. 
	 * 
	 * 			If you need to create an array of E,  use the following line as E is Comparable
	 * 			E[]  newarray = (E[]) Comparable[];
	 */

	@TimeComplexity("O(n log n)")
    @TimeComplexityAmortized("O(n log n)")
	public void sort(E[] array) {
		if(array.length < 2)return;	//This is the base case
		
	
		int middle = array.length / 2;
		E[] left = (E[]) new Comparable[middle];
		System.arraycopy(array, 0, left,0 , middle);
		

		E[] right = (E[]) new Comparable[array.length - middle];
		System.arraycopy(array, middle, right,0 ,array.length - middle);
				
		
		
		//Recursion:
		sort(left);
		sort(right);
		
		merge(left, right, array);
		
	}
	
	@TimeComplexity("O(n)")
	private <E extends Comparable<E>> void merge(E[] left, E[] right, E[] result) {
		int i = 0; int j = 0; int k = 0;
		while(i < left.length && j < right.length) {
			if(left[i].compareTo(right[j]) < 0) {
				result[k++] = left[i++];
			}
			else{
				result[k++] = right[j++];
			}
		}
	 
		while(i < left.length) {
			result[k++] = left[i++];
		}
		while(j < right.length) {
			result[k++] = right[j++];
		}
	}
}


