/*
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * 
 * If such an arrangement is impossible, it must rearrange it to the lowest possible order (i.e., sorted in ascending order).
 * 
 * The replacement must be in place and use only constant extra memory.
 * 
 *  
 *  
 *  Example 1:
 *  
 *  Input: nums = [1,2,3]
 *  Output: [1,3,2]
 *  Example 2:
 *  
 *  Input: nums = [3,2,1]
 *  Output: [1,2,3]
 *  Example 3:
 *  
 *  Input: nums = [1,1,5]
 *  Output: [1,5,1]
 *  
 *  Constraints:
 *  1 <= nums.length <= 100
 *  0 <= nums[i] <= 100
 */

public class Q018NextPermutation {
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int[] nums1 = {1,2,3};
		solution.nextPermutation(nums1);
		for (int i=0; i<nums1.length; i++) System.out.print(nums1[i]);
		
		System.out.println(" ");
		
		int[] nums2 = {3,2,1};
		solution.nextPermutation(nums2);
		for (int i=0; i<nums2.length; i++) System.out.print(nums2[i]);
		
		System.out.println(" ");
		
		int[] nums3 = {1,1,5};
		solution.nextPermutation(nums3);
		for (int i=0; i<nums3.length; i++) System.out.print(nums3[i]);
	}

	static class Solution {
		public void nextPermutation(int[] nums) {
			
			// base case
			if(nums.length == 1) return;
			
			// start from the back
			// if you run into an int smaller than the last, swap them. That is your next greatest permutation
			int curr=nums.length-2;
			int last = nums.length-1;
			while (curr > 0 && !(nums[curr] < nums[last])) {
				last = curr;
				curr--;
			}
			
			if (curr==0 && !(nums[curr] < nums[last])) {
				// return the lowest possible order by using bubble sort
				for (int i=0; i<nums.length; i++) {
					for (int j=0; j<nums.length-i-1; j++) {
						if (nums[j] > nums[j+1]) {
							int temp = nums[j];
							nums[j] = nums[j+1];
							nums[j+1] = temp;
						}
					}
				}
			}
			else {
				// return the next greatest order
				// swap the current and last values
				int temp = nums[curr];
				nums[curr] = nums[last];
				nums[last] = temp;
			}
		}
	}
	
	static class LeetCodeSolution {
	    public void nextPermutation(int[] nums) {
	        int i = nums.length - 2;
	        while (i >= 0 && nums[i + 1] <= nums[i]) {
	            i--;
	        }
	        if (i >= 0) {
	            int j = nums.length - 1;
	            while (nums[j] <= nums[i]) {
	                j--;
	            }
	            swap(nums, i, j);
	        }
	        reverse(nums, i + 1);
	    }

	    private void reverse(int[] nums, int start) {
	        int i = start, j = nums.length - 1;
	        while (i < j) {
	            swap(nums, i, j);
	            i++;
	            j--;
	        }
	    }

	    private void swap(int[] nums, int i, int j) {
	        int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	    }
	}
}
