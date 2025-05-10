/**
 * @Author: Adam Fenjiro
 * This is an implementation of the ExpressionTree class.
 * 2/14/2023
 * CS2321
 */

package cs2321;
import net.datastructures.*;

public class ExpressionTree {
	
	//Given a binary tree associated with an arithmetic expressions with operators: +,-,*,/ and decimal numbers
	//Evaluate the result and return the result. You may assume the given tree is in correct form. 
	
	//Example 1:
	//    +
	//  /  \
	// 2  4.5
	//
	// (2+4.5) = 6.5
	// Expected output: 6.5
	
	
	//Example 2:
	//
	//        +
	//      /   \
	//    *       *
	//  /  \     /  \
	//  2   -   3   2
	//     / \
	//     5  1 
	//
	// ((2*(5-1))+(3*2)) = 14
	// Expected output: 14
	
	
	public static double eval(BinaryTree<String> tree) {
		Position<String> root = tree.root();
		return evaluateExpression(root, tree); //Calls the private method defined below on the tree's root.
	}
	
	/**
	 * If the element at the position is a decimal number, returns its value. Otherwise, recursively evaluates the left and right
	 * subtrees and applies the operator at the position to the results.
	 *
	 * @param pos the root of the subtree to evaluate
	 * @param tree the binary tree representing the arithmetic expression
	 * @return the result of the arithmetic expression rooted at the given position
	 * @throws IllegalArgumentException if the element at the position is not a valid operator (+, -, *, /)
	 */
	private static double evaluateExpression(Position<String> pos, BinaryTree<String> tree) {
		String element = pos.getElement();
		if (Character.isDigit(element.charAt(0))) {
			return Double.parseDouble(element);
		} else {
			double left = evaluateExpression(tree.left(pos), tree);
			double right = evaluateExpression(tree.right(pos), tree);
			switch (element) {
				case "+":
					return left + right;
				case "-":
					return left - right;
				case "*":
					return left * right;
				case "/":
					return left / right;
				default:
					throw new IllegalArgumentException("Invalid operator: " + element);
			}
		}
	}
	
	//Given a binary tree associated with an arithmetic expressions with operators: +,-,*,/ 
	//and decimal numbers or variables
	
	// Generate the expression with parenthesis around all sub expressions except the leave nodes.  
	// You may assume the given tree is in correct form. 
	// Example:
	//        +
	//      /   \
	//    *       *
	//  /  \     /  \
	//  2   -   3   b
	//     / \
	//     a  1 
	//
	// Expected output: ((2*(a-1))+(3*b)) 
	
	public static String toExpression(BinaryTree<String> tree) {
		Position<String> root = tree.root();
		return buildExpression(root, tree); //Calls the private method defined below on the tree's root.
	}
	
	/**
	 * Recursively builds a string representation of the arithmetic expression associated with the given binary tree, with parentheses around all subexpressions except for the leaf nodes.
	 * 
	 * @param pos the current position in the binary tree to build the expression from
	 * @param tree the binary tree to build the expression from
	 * @return a string representation of the arithmetic expression with parentheses around sub-expression, except for the leaf nodes.
	 */
	private static String buildExpression(Position<String> pos, BinaryTree<String> tree) {
		String expression = pos.getElement();
	    if (tree.left(pos) != null || tree.right(pos) != null) {
	        expression = "(";
	        if (tree.left(pos) != null) {
	            expression += buildExpression(tree.left(pos), tree);
	        }
	        expression += pos.getElement();
	        if (tree.right(pos) != null) {
	            expression += buildExpression(tree.right(pos), tree);
	        }
	        expression += ")";
	    }
	    return expression;
	}
	


}
