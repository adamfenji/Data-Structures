import org.junit.*;
import jug.*;
import net.datastructures.*;
import cs2321.*;

public class TimeAddMiddlePos implements DataSeries {

	private ArrayList TARGET = init();
	private ArrayList T = init();

	public ArrayList init() {

		return new ArrayList<String>();
	
	}

	public double[] xAxis() throws Throwable {
		
			return new double[]{2, 4, 8, 16, 20, 32, 40, 64, 128, 256, 400, 512, 1024};
		
	}

	public double[] yAxis() throws Throwable {
		
			final int repeats = 2000;
			double[] xAxis = xAxis();
			double[] yAxis = new double[xAxis.length];
			List<String>[] target = (List<String>[])new List[repeats];
			int[] pos = new int[repeats];

			for(int index = 0; index < xAxis.length; index++){
				double n = xAxis[index];
				System.gc();
				// set up the Structure
				for(int r = 0; r < repeats; r++){
					target[r] = init();
					for(int count = 0; count < n; count++){
						target[r].add(0,String.valueOf(count));
					}
					pos[r] = (int) ( n / 2) ;
				}

            // Timing
				long start = System.currentTimeMillis();
				for(int r = 0; r < repeats; r++){
					target[r].add(pos[r], String.valueOf(n/2));
				}
				yAxis[index] = (double)(System.currentTimeMillis() - start) / (double)repeats;

			} // end for index
			return yAxis;
		
	}
}
