import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Q026KClosestPointstoOrigin {
	
	/*
	 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
	 * 
	 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., sqrt(x1 - x2)2 + (y1 - y2)2).
	 * 
	 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
	 * 
	 * 
	 * Example 1:
	 * Input: points = [[1,3],[-2,2]], k = 1
	 * Output: [[-2,2]]
	 * 
	 * Explanation:
	 * The distance between (1, 3) and the origin is sqrt(10).
	 * The distance between (-2, 2) and the origin is sqrt(8).
	 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
	 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
	 * 
	 * Example 2:
	 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
	 * Output: [[3,3],[-2,4]]
	 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
	 * 
	 * 
	 * Constraints:
	 * 1 <= k <= points.length <= 104
	 * -10^4 < xi, yi < 10^4
	 * 
	 */
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		LeetCodeSolution leetCodeSolution = new LeetCodeSolution();
		
		int[][] points1 = new int[][] {{1,3},{-2,2}};
		int[][] points2 = new int[][] {{3,3},{5,-1},{-2,4}};
		int k1=1;
		int k2=2;
		
		int[][] answer = solution.kClosest(points1, k1);
		for(int i=0; i<answer.length; i++) System.out.println("[" + answer[i][0] + "," + answer[i][1] + "]");
		System.out.println("----");
		answer = solution.kClosest(points2, k2);
		for(int i=0; i<answer.length; i++) System.out.println("[" + answer[i][0] + "," + answer[i][1] + "]");
		System.out.println("----");
		answer = leetCodeSolution.kClosest(points1, k1);
		for(int i=0; i<answer.length; i++) System.out.println("[" + answer[i][0] + "," + answer[i][1] + "]");
		System.out.println("----");
		answer = leetCodeSolution.kClosest(points2, k2);
		for(int i=0; i<answer.length; i++) System.out.println("[" + answer[i][0] + "," + answer[i][1] + "]");
	}
	
	static class Solution {
	    public int[][] kClosest(int[][] points, int k) {
	        int[][] kClosest = new int[k][];
	        PriorityQueue<Double> heap = new PriorityQueue<Double>(Collections.reverseOrder());
	        HashMap<Double, ArrayList<int[]>> pointMap = new HashMap<Double, ArrayList<int[]>>();

	        for(int i=0; i<points.length; i++) {

	            // calculate distance
	            double distance = distance(points[i][0], points[i][1]);

	            // add to the heap with reverse order priority
	            heap.add(distance);

	            // add point to point map for later
	            int[] point = new int[] {points[i][0], points[i][1]};
	            if (pointMap.containsKey(distance)) pointMap.get(distance).add(point);
	            else pointMap.put(distance, new ArrayList<int[]>(Arrays.asList(new int[] {points[i][0], points[i][1]})));

	            // remove items from the heap which are too large 
	            if (heap.size() > k) {
	                heap.poll(); // remove highest values
	            }
	        }

	        // find points with distances stored in heap
	        for (int i=0; i<k; i++) {
	            // get all points with this distance
	            ArrayList<int[]> pointsInI = pointMap.get(heap.poll());
	            
	            // add each point to kClosest until it is filled
	            for (int j=0; j<pointsInI.size(); j++) if (i + j < k) {
	                kClosest[i+j] = pointsInI.get(j); 
	            }
	            
	            // increment i and remove heap distances by the number of points added
	            for (int j=0; j<pointsInI.size()-1; j++) {
	                heap.poll();
	                i++;
	            }
	        }
	        return kClosest;
	    }
	    
	    public double distance(int x1, int y1) {
	    	return Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
	    }
	}
	
	static class LeetCodeSolution {
	    public int[][] kClosest(int[][] points, int k) {
	        return quickSelect(points, k);
	    }
	    
	    private int[][] quickSelect(int[][] points, int k) {
	        int left = 0, right = points.length - 1;
	        int pivotIndex = points.length;
	        while (pivotIndex != k) {
	            // Repeatedly partition the array
	            // while narrowing in on the kth element
	            pivotIndex = partition(points, left, right);
	            if (pivotIndex < k) {
	                left = pivotIndex;
	            } else {
	                right = pivotIndex - 1;
	            }
	        }
	        
	        // Return the first k elements of the partially sorted array
	        return Arrays.copyOf(points, k);
	    }

	    private int partition(int[][] points, int left, int right) {
	        int[] pivot = choosePivot(points, left, right);
	        int pivotDist = squaredDistance(pivot);
	        while (left < right) {
	            // Iterate through the range and swap elements to make sure
	            // that all points closer than the pivot are to the left
	            if (squaredDistance(points[left]) >= pivotDist) {
	                int[] temp = points[left];
	                points[left] = points[right];
	                points[right] = temp; 
	                right--;
	            } else {
	                left++;
	            }
	        }
	        
	        // Ensure the left pointer is just past the end of
	        // the left range then return it as the new pivotIndex
	        if (squaredDistance(points[left]) < pivotDist)
	            left++;
	        return left;
	    }

	    private int[] choosePivot(int[][] points, int left, int right) {
	        // Choose a pivot element of the array
	        return points[left + (right - left) / 2];
	    }
	    
	    private int squaredDistance(int[] point) {
	        // Calculate and return the squared Euclidean distance
	        return point[0] * point[0] + point[1] * point[1];
	    }
	}
}
