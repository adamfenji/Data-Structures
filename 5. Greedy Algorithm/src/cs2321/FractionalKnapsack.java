package cs2321;

/**
 * Implementation of Fractional Knapsack.
 * CS2321
 * @author Adam Fenjiro
 */

import net.datastructures.Entry;
public class FractionalKnapsack {

   
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		double unitPrice; 
		int volume; 
		double value = 0;
		int take = 0;
		//Storing data:
		 HeapAPQ data = new HeapAPQ();
		 for(int i = 0; i < items.length; i++) {
			 data.insert(((double)items[i][1]/items[i][0]) * -1, items[i][0]);
		 }
		 //While the storage of our container is bigger than 0 and the data size is bigger than 0
		 while (knapsackWeight > 0 && data.size() > 0) { 
			 Entry<Double, Integer> e = data.removeMin();
			 unitPrice = e.getKey() * -1; 
			 volume = e.getValue();
			 if (knapsackWeight >= volume) {
				  take = volume;
				  knapsackWeight = knapsackWeight - volume; 
			 }
			 else {
				take = knapsackWeight; 
				knapsackWeight = knapsackWeight - take;

			 }
			 value = value + take * unitPrice;
		 }
		return value;
	}
}
