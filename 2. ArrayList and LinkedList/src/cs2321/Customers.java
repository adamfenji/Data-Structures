/**
 * @author Adam Fenjiro
 * CS2321
 * 2/7/2023
 * 
 * This is an implementation of the Customer class.
 */
package cs2321;

import net.datastructures.*;
import net.datastructures.List; 
import net.datastructures.Position; 
import net.datastructures.PositionalList; 
import java.util.Iterator; 

/*
 * The design of this program is related to Cache replacement policy in OS 
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)
 * 
 * LRU: Discards the least recently used items first.
 * 
 * Let's say we have a online store and would like to keep a list of certain number of customers
 * who visited our site from the most recent to the least recent.  
 * 
 * For example: 
 * 
 *  Let's say K=4, which means we only keep the most recent 4 customers. 
 *  And the visiting sequence of the customers: A B C D E D F. Each letter represent a customer's name.
 *
 *  The recent list will change as customers coming:
 *   
 *   A          -- A is the first customer
 *   B A        -- B is the next customer. B is not on the list and B's visit is sooner than A's
 *   C B A      -- C is the next customer. C is not on the list and C's visit is sooner than B's
 *   D C B A    -- D is the next customer. D is not on the list and D's visit is sooner than C's
 *   E D C B    -- E is the next customer. E is not on the list. But The list is full. 
 *                 Kick out A as it is the least recent one. 
 *                 Add E to the most recent one. 
 *              
 *   D E C B    -- D is the next customer. D is on the list already.  
 *                 D's place is moved from second to the first. 
 *   
 *   F D E C    -- F is the next customer. F is not on the list. The list is full.
 *                 Kick out B and put F as the most recent one 
 *   
 *  For simplicity we only keep the customer name in the list. 
 *  But to make the list useful, we will store some other information associated with the customer.  
 * 
 */
 

public class Customers {
	DoublyLinkedList<String> list;
    int K;
	
	
	/* Keep up to K customers who visits us recently from the most recent to the least recent. */
	public Customers(int K) {
		this.K = K;
        list = new DoublyLinkedList<>(); 
	}

	/* customer visits us, one at a time */ 
	public void visit(String customer) {
		if(list.find(customer) != null) { 		// while list is not  null
            list.remove(list.find(customer));	//will remove the element repeated
            list.addFirst(customer);			//will add it to the beginning
        }
        else if (K == list.size()) {			//if it is full
        	list.removeLast();					//remove last value 
        	list.addFirst(customer);			//add the most recent
        }
        else list.addFirst(customer);	
	};
	
	/* return the most recent K customers */
	public PositionalList<String> getList() {
		return list;
	}
		
	/* start a new customer list.  
	 */
	public void resetList() {
		Iterator<String> list2 = list.iterator();
        while(list2.hasNext()) {
            System.out.println(list2.next());}
	}
	
}
