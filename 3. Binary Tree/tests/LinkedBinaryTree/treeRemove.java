import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;
import java.util.Iterator;

@jug.SuiteName("remove")
public class treeRemove {

	private LinkedBinaryTree<String> TARGET = init();
	private LinkedBinaryTree<String> T = init();

	public LinkedBinaryTree<String> init() {
		return new LinkedBinaryTree<String>();
	}

	@Before
	public void setup() throws Throwable {
		
	}

	@org.junit.Test(timeout=1000)
	@jug.TestName("Test1 remove(B) -> A:(left:C,right:null)")
	public void Test1() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
		Position<String> n2 = TARGET.addLeft(n1, "B");
		Position<String> n3 = TARGET.addLeft(n2, "C");
		TARGET.remove(n2);
		
		org.junit.Assert.assertEquals("Test remove(B) -> A:(left:C,right:null)", (Object)(TARGET.left(n1)), (Object)(n3));
	}

	@org.junit.Test()
	@jug.TestName("Test2 remove(A) -> B:(left:C,right:null)")
	public void Test2() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
		Position<String> n2 = TARGET.addLeft(n1, "B");
		Position<String> n3 = TARGET.addLeft(n2, "C");
		TARGET.remove(n1);
		
		org.junit.Assert.assertEquals("Test remove(A) -> B:(left:C,right:null)", (Object)(TARGET.root()), (Object)(n2));
	}
	

	@org.junit.Test()
	@jug.TestName("Test3 remove(C) ")
	public void Test3() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
		Position<String> n2 = TARGET.addLeft(n1, "B");
		Position<String> n3 = TARGET.addRight(n1, "C");
		Position<String> n4 = TARGET.addRight(n3, "D");
		Position<String> n5 = TARGET.addLeft(n4, "E");
		Position<String> n6 = TARGET.addRight(n4, "F");
		
		
		TARGET.remove(n3);
		
		org.junit.Assert.assertEquals("A's right child is D", n4, (Object)(TARGET.right(n1)));
		
		org.junit.Assert.assertEquals("D's parent is A", n1, (Object)(TARGET.parent(n4)));
	}
	
	
	@org.junit.Test(expected=IllegalArgumentException.class)
	@jug.TestName("Test4 remove(D) throw exception ")
	public void Test4() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
		Position<String> n2 = TARGET.addLeft(n1, "B");
		Position<String> n3 = TARGET.addRight(n1, "C");
		Position<String> n4 = TARGET.addRight(n3, "D");
		Position<String> n5 = TARGET.addLeft(n4, "E");
		Position<String> n6 = TARGET.addRight(n4, "F");
		
		
		TARGET.remove(n4);
		
	}
	

}
