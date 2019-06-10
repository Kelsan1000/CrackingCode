import java.util.*;
//Linked Lists
public class c2 {
	//Question 1: Remove Dupes! Write code to remove duplicates from an unsorted linked list.
	//How would you solve this problem if a temporary buffer is not allowed?
	void deleteDups(LinkedListNode n) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode previous = null;
		while (n != null) {
			if (set.contains(n.data)) {
				previous.next = n.next;
			}
			else {
				set.add(n.data);
				previous = n;
			}
			n = n.next;
		}
	}
	
	//NO BUFFER
	void deleteDupes(LinkedListNode head) {
		LinkedListNode current = head;
		while (current != null) {
		/* Remove all future nodes that have the same value */
			LinkedListNode runner = current;
			while (runner.next != null) {
				if (runner.next.data == current.data) {
					runner.next = runner.next.next; } 
				else {
						runner = runner.next;
				}
			}
			current = current.next;
		}
	}
	
	//Question 2: Return Kth to Last: Implement an algorithm to find the kth to last
	//element of a singly linked list.
	
	int printKthToLast(LinkedListNode head, int k) {
		if (head== null) {
			return 0;
		}
		int index = printKthToLast(head.next, k) + 1;
		if (index == k) {
			System.out.println(k + "th to last node is " + head.data);
		}
		return index;
	}
	
	LinkedListNode nthTolast(LinkedListNode head, int k) {
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		/* Move p1 k nodes into the list.*/
		for (int i= 0; i < k; i++) {
			if (p1 == null) return null; // Out of bounds
			p1 = p1.next;
		}
		
		/* Move them at the same pace. When pl hits the end, p2 will be at the right
		 * element. */
		while (p1!= null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
	//Question 3: Delete Middle Node: Implement an algorithm to delete a node in the middle
	//(i.e., any node but the first and last node, not necessarily the exact middle) of a
	//singly linked list, given only access to that node.
	//EXAMPLE 
	//Input: the node c from the linked list a->b->c->d->e->f
	//Result: nothing is returned but the new linked list looks like a->b->d->e->f
	
	boolean deleteNode(LinkedListNode n) {
		if (n == null || n.next == null) { 
			return false; // Failure
		}
		LinkedListNode next = n.next;
		n.data= next.data;
		n.next = next.next;
		return true;
	}
	
	//Question 4: Partition: Write the code to partition a linked list around a value x, such that 
	//all nodes less than x come before all nodes greater than or equal to x. If x is contained
	//within the list, the values of x only need to be after the elements less than x (see below).
	//The partition element x can appear anywhere in the "right partition"; it does not need to 
	//appear between the left and right partitions.
	//EXAMPLE
	//Input: 3->5->8->-5->-10->2->1 [partition = 5]
	//Result: 3->1->2->10->5->5->8
	
	LinkedListNode partition(LinkedListNode node, int x) {
		LinkedListNode head = node;
		LinkedListNode tail = node;
		while (node != null) {
			LinkedListNode next = node.next;
			if (node.data < x) {
				/* Insert node at head. */
				node.next= head;
				head= node;
			} else {
			/* Insert node at tail. */
			tail.next= node;
			tail= node;
			}
			node = next;
		}
		tail.next = null;
		// The head has changed, so we need to return it to the user.
		return head;
	}
			
	
	//Question 5: Sum Lists: You have two numbers represented by a linked list, where each node
	//contains a single digit. The digits are stored in reverse order, such that the 1's
	//digit is at the head of the list. Write a function that adds the two numbers and returns 
	//the sum as a linked list.
	//EXAMPLE
	//Input: (7->1->6) + (5->9->2). That is, 617 + 295.
	//Output: 2->1->9. That is 912.
	//Suppose the digits are stored in forward order. Repeat the above problem.
	//(6->1->7) + (2->9->5) => 9->1->2
	
	LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
		if (l1 ==null && l2== null && carry== 0) {
			return null;
		}
		LinkedListNode result = new LinkedListNode();
		int value = carry;
		if (l1 != null) {
			value += l1.data;
		}
		if (l2 != null) {
			value += l2.data;
		}
		result.data = value%10; //second digit of number
		//recurse
		if (l1 != null || l2 != null) {
			LinkedListNode more = addLists(l1==null?null:l1.next,
											l2 == null?null:l2.next,
											value >=10?1:0);
			result.setNext(more);
		}
		return result;
	}
	
	
	//Question 6: Palindrome: Implement a function to check if a linked list is a palindrome
	
	
	
	//Question 7: Intersection: Given two (singly) linked lists, determine if the two lists 
	//intersect. Return the intersecting node. Note that the intersection is defined based 
	//on reference, not value. That is, if the kth node on the first linked list is the
	//exact same node (by reference) as the jth node of the second linked list, then they are
	//intersecting
	
	//Question 8: Loop Detection: Given a circular linked list, implement an algorithm
	//that returns the node at the beginning of the loop
	//DEFINITION
	//Circular Linked List: A (corrupt) linked list in which a node's next pointer
	//points to an earlier node, so as to make a loop in the linked list
	//EXAMPLE
	//Input: A->B->C->D->E->C [the same C as earlier]
	//Output: C
	
	LinkedListNode FindBeginning(LinkedListNode head) {
		LinkedListNode slow = head;
		LinkedListNode fast = head;
		//Find meeting point
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {//collision
				break;
			}
		}

		//Error check: no meeting point => no loop
		if (fast == null || fast.next == null) {
			return null;
		}
		slow = head;
		while(slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}
}
