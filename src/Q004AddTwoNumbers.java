import java.util.ArrayList;
import java.util.List;

public class Q004AddTwoNumbers {
	/*
	 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 * 
	 * Example 1:
	 * Input: l1 = [2,4,3], l2 = [5,6,4]
	 * Output: [7,0,8]
	 * Explanation: 342 + 465 = 807.
	 * 
	 * Example 2:
	 * Input: l1 = [0], l2 = [0]
	 * Output: [0]
	 * 
	 * Example 3:
	 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
	 * Output: [8,9,9,9,0,0,0,1]
	 * 
	 * Constraints:
	 *  • The number of nodes in each linked list is in the range [1, 100].
	 * 	• 0 <= Node.val <= 9
	 * 	• It is guaranteed that the list represents a number that does not have leading zeros.
	 * 
	 */
	
	public static void main(String[] args) {
		
	}
	
	public class Solution {
		
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        List<Integer> sums = new ArrayList<Integer>();
	        int power = 0;
	        ListNode currentL1 = l1;
	        ListNode currentL2 = l2;
	        while (currentL1 != null || currentL2 != null) {
	            int addend1, addend2;
	            if (currentL1 == null) addend1 = 0;
	            else addend1 = currentL1.val;
	            if (currentL2 == null) addend2 = 0;
	            else addend2 = currentL2.val;
	            int sum = (addend1 + addend2) * ((int) Math.pow(10, power));
	            sums.add(sum);
	            power += 1;
	            System.out.println(sum);
	            
	            if (currentL1 != null) currentL1 = currentL1.next;
	            if (currentL2 != null) currentL2 = currentL2.next;
	        }
	        
	        int sum = 0;
	        for (int i=0; i < sums.size(); i++) {
	            sum += sums.get(i);
	        }
	        
	        ListNode resultCurrent = new ListNode();
	        ListNode result = resultCurrent;
	        String sumString = String.valueOf(sum);        
	        for (int j = sumString.length() - 1; j >= 0; j--) {
	            resultCurrent.val = Character.getNumericValue(sumString.charAt(j));
	            if (j != 0) {
	                resultCurrent.next = new ListNode();
	                resultCurrent = resultCurrent.next;
	            }
	        }
	        
	        return result;
	    }
		
		public ListNode leetCodeAddTwoNumbers(ListNode l1, ListNode l2) {
		    ListNode dummyHead = new ListNode(0);
		    ListNode p = l1, q = l2, curr = dummyHead;
		    int carry = 0;
		    while (p != null || q != null) {
		        int x = (p != null) ? p.val : 0;
		        int y = (q != null) ? q.val : 0;
		        int sum = carry + x + y;
		        carry = sum / 10;
		        curr.next = new ListNode(sum % 10);
		        curr = curr.next;
		        if (p != null) p = p.next;
		        if (q != null) q = q.next;
		    }
		    if (carry > 0) {
		        curr.next = new ListNode(carry);
		    }
		    return dummyHead.next;
		}
	}
}

//Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
