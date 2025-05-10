import org.junit.*;
import jug.*;
import net.datastructures.*;
import cs2321.*;

public class DLTimeAddFirst implements DataSeries {

	private DoublyLinkedList<String> TARGET = init();
	private DoublyLinkedList<String> T = init();

	public DoublyLinkedList<String> init() {

		return new DoublyLinkedList<String>();
	
	}

	public double[] xAxis() throws Throwable {
		
			return new double[]{2, 4, 8, 16, 20, 32, 40, 64, 128, 256, 400, 512, 1024};
		
	}

	public double[] yAxis() throws Throwable {
		
			final int repeats = 2000;
			double[] xAxis = xAxis();
			double[] yAxis = new double[xAxis.length];
			PositionalList<String>[] target = (PositionalList<String>[])new PositionalList[repeats];

			for(int index = 0; index < xAxis.length; index++){
				double n = xAxis[index];
				System.gc();
				
				// set up the Structure to have n elements
				for(int r = 0; r < repeats; r++){
					target[r] = init();
					for(int count = 0; count < n; count++){
						target[r].addFirst(String.valueOf(count));
					}
				}

            // Timing
				long start = System.currentTimeMillis();
				for(int r = 0; r < repeats; r++){
					target[r].addFirst(String.valueOf(n/2));
				}
				yAxis[index] = (double)(System.currentTimeMillis() - start) / (double)repeats;

			} // end for index
			return yAxis;
		
	}
}
