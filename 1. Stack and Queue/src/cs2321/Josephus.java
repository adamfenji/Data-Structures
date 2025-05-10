/**
 * @Author: Adam Fenjiro
 * This is an implementation of Josephus.
 * 1/31/2023
 * CS2321
 */
 
package cs2321;
//computes the winner of the Josephus problem using circular queue 
public class Josephus{
    /**
     * All persons sit in a circle. When we go around the circle, initially starting
     * from the first person, then the second person, then the third...
     * we count 1,2,3,.., k-1. The next person, that is the k-th person is out.
     * Then we restart the counting from the next person, go around, the k-th person
     * is out. Keep going the same way, when there is only one person left, she/he
     * is the winner.
     *
     * @parameter persons  an array of string which contains all player names.
     * @parameter k  an integer specifying the k-th person will be kicked out of the game
     * @return return a array in the order when the players were out of the game.
     *         The last one in the array is the winner.
     */

	
    public String[] order(String[] persons, int k ) {
        CircularArrayQueue<String> listArray = new CircularArrayQueue(persons.length);
        String [] arr = new String [persons.length];

        for(int i = 0; i < persons.length ; i++) { //This passes the values to the list array
            listArray.enqueue(persons[i]);
        }
        if (listArray.isEmpty()) return null;
        int count = 0;

        while(listArray.size() >= 1) {			
            for (int i = 0; i < k - 1; i++) {   //we use k -1 because when we get to k, we really delete the value without returning it
                String x = listArray.dequeue(); //will remove the front element an make it a variable
                listArray.enqueue(x); 		    // putting back the removed value, but because of the circular array, the value after the first one will become the head
            }
            arr[count] = listArray.dequeue();	//we pass the values that are being deleted
                count++;						//we increment the count
        }
        return arr;								//This return the order in which the participants where deleted 
    }
    
}

