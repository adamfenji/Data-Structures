/**
 * @Author: Adam Fenjiro
 * This is an implementation of a program that will evaluate a given postfix expression.
 * 1/31/2023
 * CS2321
 */ 

package cs2321;

public class PostfixExpression {

    /**
     * Evaluate a postfix expression.
     * Postfix expression notation has operands first, following by the operations.
     * For example:
     *    13 5 *           is same as 13 * 5
     *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6
     *
     * In this homework, expression in the argument only contains
     *     integer, +, -, *, / and a space between every number and operation.
     * You may assume the result will be integer as well.
     *
     * @param exp The postfix expression
     * @return the result of the expression
     */
    public static int  evaluate(String exp) {
        LinkedListStack<Integer> listStack = new LinkedListStack();
        String[] token = exp.split(" ");
        
        //initializing the values:
        int val1;
        int val2;
       
        //will create a loop that will go through all the characters  
        for(int i = 0; i < token.length;i++){
        	
        	
            if (token[i].equals("+")) {			//if the string is equal +
                val1 = listStack.pop();			//set val1 
                val2 = listStack.pop();			//set val2
                listStack.push( val1 +  val2); 
            }
            else if(token[i].equals("-")){ 		//if the string is equal - it will subtract val2 from val1
                val1 = listStack.pop();
                val2 = listStack.pop();
                listStack.push(val2 - val1);
            }
            else if (token[i].equals("*")){ 	//if the string is equal to *, then multiply va1 * val2
                val1 = listStack.pop();
                val2 = listStack.pop();
                listStack.push(val1 * val2);
            }
            else if (token[i].equals("/")){
                val1 = listStack.pop();
                val2 = listStack.pop();
                listStack.push(val2 / val1);
            }
            else {								//else, this will take the string, convert it into an integer and add it to the list 
                listStack.push(Integer.valueOf(token[i]));
            }
        }
        if (listStack.isEmpty()) return 0;		//if it is empty this will return 0
        return listStack.pop();

    }
}
