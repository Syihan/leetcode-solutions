import java.util.HashMap;

/*
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 * 
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * 
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * 
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class Q014SubarraySumEqualsK {
	public static void main(String[] arg) {
		int[] nums1 = {1,1,1};
		int k1 = 2;
		
		int[] nums2 = {1,2,3};
		int k2 = 3;
		
		int[] nums3 = {1,-1,0};
		int k3 = 0;
		
		int[] nums4 = {0, 0};
		int k4 = 0;
		
		System.out.println(subarraySum(nums1, k1));
		System.out.println(subarraySum(nums2, k2));
		System.out.println(subarraySum(nums3, k3));
		System.out.println(subarraySum(nums4, k4));
	}
	
	public static int subarraySum(int[] nums, int k) {
		
		int subarraySum = 0;
		for(int i=0; i<nums.length; i++) {
			int runningTotal = nums[i];
			if (runningTotal == k) {
				subarraySum++;
			}
			for (int j=i+1; j<nums.length; j++) {
				if(runningTotal != k) {
					runningTotal += nums[j];
					if (runningTotal == k) {
						subarraySum++;
					}
				}
				else if (runningTotal + nums[j] == k) {
					subarraySum++;
				}
				if (runningTotal > k) break;
			}
		}
		
        return subarraySum;
    }
	
	public static int leetCodeSubarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
