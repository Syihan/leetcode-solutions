import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q0203Sum {
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		LeetCodeSolution leetCodeSolution = new LeetCodeSolution();
		
		int[] nums1 = {-1,0,1,2,-1,-4};
		// [-4, -1, -1, 0, 1, 2]
		System.out.println(solution.threeSum(nums1));
		System.out.println(leetCodeSolution.threeSum(nums1));
		
		int[] nums2 = {};
		System.out.println(solution.threeSum(nums2));
		System.out.println(leetCodeSolution.threeSum(nums2));
		
		int[] nums3 = {0};
		System.out.println(solution.threeSum(nums3));
		System.out.println(leetCodeSolution.threeSum(nums3));
	}

	static class Solution {
	    public List<List<Integer>> threeSum(int[] nums) {
	        List<Integer> numsList = new ArrayList<Integer>();
	        List<List<Integer>> list = new ArrayList<List<Integer>>();
	        List<List<Integer>> indexCombos = new ArrayList<List<Integer>>();
	        
	        // base case, return empty list
	        if (nums.length < 3) return list;
	        
	        for (int i=0; i<nums.length; i++) numsList.add(nums[i]);
	        
	        // sort numsList
	        Collections.sort(numsList);
	        
	        // find triplets that sum to 0
	        int target = 0;
	        for (int i=0; i<numsList.size(); i++) {	// from the front
	        	int iVal = numsList.get(i);
	        	for (int j=numsList.size()-1; j>=0; j--) {	// from the back
	        		int jVal = numsList.get(j);
	        		int kVal = target - iVal - jVal;
	        			        		
	        		// find the third triplet value
	        		int k = numsList.indexOf(kVal);
	        		
	        		// if not found, continue to the next j value
	        		if (k == -1) continue;
	        		
	        		// if any of the indices match, continue to the next j value
	        		if (i == j || i == k || j == k) continue;
	        		
	        		// check if index combination already exists
	        		ArrayList<Integer> indexCombo = new ArrayList<Integer>(Arrays.asList(i, j, k));
	        		Collections.sort(indexCombo);
	        		
	        		// log index combo if the combo doesn't already exist
	        		if (!indexCombos.contains(indexCombo)) {
	        			indexCombos.add(indexCombo);
	        			
	        			// log values if the combo doesn't already exist
	        			ArrayList<Integer> triplets = new ArrayList<Integer>(Arrays.asList(iVal, jVal, kVal));
	        			Collections.sort(triplets);
	        			if (!list.contains(triplets)) list.add(triplets);
	        		}
	        	}
	        }
	        return list;
	    }
	}
	
	static class LeetCodeSolution {
	    public List<List<Integer>> threeSum(int[] nums) {
	        Set<List<Integer>> res = new HashSet<>();
	        Set<Integer> dups = new HashSet<>();
	        Map<Integer, Integer> seen = new HashMap<>();
	        for (int i = 0; i < nums.length; ++i)
	            if (dups.add(nums[i])) {
	                for (int j = i + 1; j < nums.length; ++j) {
	                    int complement = -nums[i] - nums[j];
	                    if (seen.containsKey(complement) && seen.get(complement) == i) {
	                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
	                        Collections.sort(triplet);
	                        res.add(triplet);
	                    }
	                    seen.put(nums[j], i);
	                }
	            }
	        return new ArrayList<List<Integer>>(res);
	    }
	}
}
