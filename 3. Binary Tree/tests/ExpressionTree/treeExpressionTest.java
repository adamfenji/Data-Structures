import static org.junit.Assert.*;
import net.datastructures.*;

import org.junit.Before;
import org.junit.Test;
import cs2321.LinkedBinaryTree;
import cs2321.ExpressionTree;

public class treeExpressionTest {

	LinkedBinaryTree<String> T; 
	
	@Before
	public void setUp() throws Exception {
		T = new LinkedBinaryTree<String>();
	}

	@Test
	@jug.TestName(" Tree of (2+4.5) ")
	public void testEval1() {
		
		// 2 + 4.5 
		Position<String> v1 = T.addRoot("+");
		T.addLeft(v1,"2");
		T.addRight(v1,  "4.5");
		Double expected=6.5;
		Double ret = ExpressionTree.eval(T);
		assertEquals(expected, ret, 0.000000);
		
	}

	@Test
	@jug.TestName(" Tree of (2+4.5) ")
	public void testToExpression1() {
		// 2 + 4.5 
		Position<String> v1 = T.addRoot("+");
		T.addLeft(v1,"2");
		T.addRight(v1, "4.5");
		String expected= "(2+4.5)";
		String ret = ExpressionTree.toExpression(T);
		assertEquals(expected, ret);
	}
	

	@Test
	@jug.TestName(" Tree of ((2*(5-1))+(3*2)) = 14 ")
	public void testEval2() {
		
		//        +
		//      /   \
		//    *       *
		//  /  \     /  \
		//  2   -   3   2
		//     / \
		//     5  1 
		//
		// ((2*(5-1))+(3*2)) = 14
		Position<String> v1 = T.addRoot("+");
		
		Position<String> v2 = T.addLeft(v1,"*");
		Position<String> v3 = T.addRight(v1,  "*");
		
		Position<String> v4 = T.addLeft(v2,"2");
		Position<String> v5 = T.addRight(v2,"-");
		

		Position<String> v6 = T.addLeft(v3,"3");
		Position<String> v7 = T.addRight(v3,"2");
		
		Position<String> v8 = T.addLeft(v5,"5");
		Position<String> v9 = T.addRight(v5,"1");
		
		
		
		Double expected=14.0;
		Double ret = ExpressionTree.eval(T);
		assertEquals(expected, ret, 0.000000);
		
	}

	@Test
	@jug.TestName("Tree of ((2*(a-1))+(3*b)) ")
	public void testToExpression2() {
		//        +
		//      /   \
		//    *       *
		//  /  \     /  \
		//  2   -   3   b
		//     / \
		//     a  1 
		//
		// ((2*(a-1))+(3*b)) 
		
		Position<String> v1 = T.addRoot("+");
		
		Position<String> v2 = T.addLeft(v1,"*");
		Position<String> v3 = T.addRight(v1,  "*");
		
		Position<String> v4 = T.addLeft(v2,"2");
		Position<String> v5 = T.addRight(v2,"-");
		

		Position<String> v6 = T.addLeft(v3,"3");
		Position<String> v7 = T.addRight(v3,"b");
		
		Position<String> v8 = T.addLeft(v5,"a");
		Position<String> v9 = T.addRight(v5,"1");
		
		String expected="((2*(a-1))+(3*b))";
		
		String ret = ExpressionTree.toExpression(T);
		
		assertEquals(expected, ret);
	}
	

}
