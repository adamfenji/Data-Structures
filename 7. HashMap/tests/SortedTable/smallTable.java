import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;

@jug.SuiteName("Small Map")
public class smallTable {

	private SortedTable<String, String> TARGET = init();
	private SortedTable<String, String> T = init();

	public SortedTable<String, String> init() {
		return new SortedTable<String, String>();
	}

	@org.junit.Test(timeout=60000)
	@jug.TestName(".put(\"1\", \"A\"): Verifying isEmpty() equals false")
	public void Test1() throws Throwable {
		TARGET.put("1", "A");
		
		org.junit.Assert.assertEquals(".put(\"1\", \"A\"): Verifying isEmpty() equals false", (Object)(false), (Object)(TARGET.isEmpty()));
	}

	@org.junit.Test()
	@jug.TestName(".put(\"2\", \"B\"): Verifying get(2) equals \"B\"")
	public void Test2() throws Throwable {
		TARGET.put("2", "B");
		
		org.junit.Assert.assertEquals(".put(\"2\", \"B\"): Verifying get(2) equals \"B\"", (Object)("B"), (Object)(TARGET.get("2")));
	}

	@org.junit.Test()
	@jug.TestName(".put(\"A\", \"1\"): Verifying size() equals 1")
	public void Test3() throws Throwable {
		TARGET.put("A", "1");
		
		org.junit.Assert.assertEquals(".put(\"A\", \"1\"): Verifying size() equals 1", (Object)(1), (Object)(TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName(".put(\"3\", \"A\"): .put(\"5\", \"C\"): put(\"7\",\"D\"): Verifying size() equals 3")
	public void Test4() throws Throwable {
		TARGET.put("3", "A");
		TARGET.put("5", "C");
		TARGET.put("7", "D");
		
		org.junit.Assert.assertEquals(".put(\"3\", \"A\"): .put(\"5\", \"C\"): put(\"7\",\"D\"): Verifying size() equals 3", (Object)(3), (Object)(TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName(".put(\"1\", \"C\"): Verifying put(\"1\",\"A\") equals \"C\"")
	public void Test5() throws Throwable {
		TARGET.put("1", "C");
		
		org.junit.Assert.assertEquals(".put(\"1\", \"C\"): Verifying put(\"1\",\"A\") equals \"C\"", (Object)("C"), (Object)(TARGET.put("1", "A")));
	}

	@org.junit.Test()
	@jug.TestName(".put(\"1\", \"C\"): .put(\"3\", \"D\"): .put(\"1\", \"F\") Verifying put(\"1\",\"A\") equals \"F\"")
	public void Test6() throws Throwable {
		TARGET.put("1", "C");
		TARGET.put("3", "D");
		TARGET.put("1", "F");
		
		org.junit.Assert.assertEquals(".put(\"1\", \"C\"): .put(\"3\", \"D\"): .put(\"1\", \"F\") Verifying put(\"1\",\"A\") equals \"F\"", (Object)("F"), (Object)(TARGET.put("1", "A")));
	}

	@org.junit.Test()
	@jug.TestName(".put(\"1\", \"A\"): .put(\"1\", \"B\"): Verifying .get(\"1\") equals \"B\"")
	public void Test7() throws Throwable {
		TARGET.put("1", "A");
		TARGET.put("1", "B");
		
		org.junit.Assert.assertEquals(".put(\"1\", \"A\"): .put(\"1\", \"B\"): Verifying .get(\"1\") equals \"B\"", (Object)("B"), (Object)(TARGET.get("1")));
	}

	@org.junit.Test()
	@jug.TestName(".put(\"1\", \"A\"): .put(\"1\", \"B\"): Verifying .size() equals \"B\"")
	public void Test8() throws Throwable {
		TARGET.put("1", "A");
		TARGET.put("1", "B");
		
		org.junit.Assert.assertEquals(".put(\"1\", \"A\"): .put(\"1\", \"B\"): Verifying .size() equals \"B\"", (Object)(1), (Object)(TARGET.size()));
	}

}
