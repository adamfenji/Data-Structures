import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;

@jug.SuiteName("Large Item List: 10000 items [n[i],i], where n[0] is \"1234\" and n[i]=(721*x(n-1)+51)%10000 (as a string)")
public class largeItemListHMSize {

	private HashMap<String, String> TARGET = init();
	private HashMap<String, String> T = init();

	public HashMap<String, String> init() {
		return new HashMap<String, String>();
	}

	@org.junit.Test(timeout=60000)
	@jug.TestName("Verifying tableSize() = 17408")
	public void Test1() throws Throwable {
		Integer [] value2key = new Integer[10000];
		Integer [] key2value = new Integer[10000];
		int val = 1234;
		for(int i=0; i<10000; i++)
			{
				value2key[i]=val;
				key2value[val]=i;
				TARGET.put(""+val, ""+i);
				val = (721 * val + 51) % 10000;
			}
		
		org.junit.Assert.assertEquals("Verifying tableSize() = 17408", (Object)(17408), (Object)(TARGET.tableSize()));
	}

}
