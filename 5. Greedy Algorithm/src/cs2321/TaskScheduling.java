package cs2321;
import net.datastructures.Entry;

/**
 * Implementation of Task Scheduling.
 * CS2321
 * @author Adam Fenjiro
 */

public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines. 
	 * 
	 *       
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */
   public static int NumOfMachines(int[][] tasks) {
	   HeapAPQ data = new HeapAPQ();
		 HeapAPQ machine = new HeapAPQ();
		 int m = 0;
		 
		 //Inserting the tasks to the PriorityQueue
		 for(int i = 0; i < tasks.length; i++) {
			 data.insert(tasks[i][0], tasks[i][1]); 
		 }
		 while (data.size() > 0) {
			 Entry<Integer,Integer> e = data.removeMin();
			 int startTime  = e.getKey(); 
			 int endTime = e.getValue(); 
			 if (machine.isEmpty()){  
				 machine.insert(endTime, 0);
				 
				 }
			 
			 else if(startTime >= (int)machine.min().getKey()) {
				 machine.replaceKey(machine.min(),e.getValue());
			 }
			 
			 else
			machine.insert(endTime, 0);
			  
		 }
	  return machine.size();
 }
}
