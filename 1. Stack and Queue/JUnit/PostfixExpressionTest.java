/**
 * @Author: Adam Fenjiro
 * This is an JUnit test for PostFixExpression.
 * 1/31/2023
 * CS2321
 */

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import cs2321.PostfixExpression;

public class PostfixExpressionTest {

	@Test
	public void testEvaluateEmpty() {
        String exp1= " ";
		Assert.assertEquals(0,PostfixExpression.evaluate(exp1));
	}
	@Test
	public void testEvaluateSubstraction() {
        String exp1 = "5 20 -";
		Assert.assertEquals(-15,PostfixExpression.evaluate(exp1));
	}
	@Test
	public void testEvaluateSum() {
        String exp1 = "5 20 +";
		Assert.assertEquals(25,PostfixExpression.evaluate(exp1));
	}
	@Test
	public void testEvaluateDiv() {
        String exp1 = "20 5 /";
		Assert.assertEquals(4,PostfixExpression.evaluate(exp1));
	}
	@Test
	public void testEvaluateMultiplication() {
        String exp1 = "5 20 *";
		Assert.assertEquals(100,PostfixExpression.evaluate(exp1));
	}
	
	@Test
	public void testEvaluate2() {
		String exp1 = "5 20 5 2 * + 3 / -";
		Assert.assertEquals(-5,PostfixExpression.evaluate(exp1));
	}
	@Test
	public void testEvaluate3() {
        String exp1 = "3 4 5 * -";
		Assert.assertEquals(-17,PostfixExpression.evaluate(exp1));
	}
}
