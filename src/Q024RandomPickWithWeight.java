public class Q024RandomPickWithWeight {
	
	public static void main(String args[]) {
		
		Solution solution = new Solution(new int[] {1});
		// System.out.println(solution.pickIndex());
		
		solution = new Solution(new int[] {1, 3});
		System.out.println(solution.pickIndex());
		System.out.println(solution.pickIndex());
		System.out.println(solution.pickIndex());
		System.out.println(solution.pickIndex());
		System.out.println(solution.pickIndex());
	}
	
	static class Solution {

		int[] Weights;
		int Sum = 0;
		
	    public Solution(int[] w) {
	    	Weights = w;
	    	
	        // find the sum
	    	for(int i=0; i<Weights.length; i++) Sum += w[i];
	    }
	    
	    public int pickIndex() {
	        int randomGenerated = (int)(Math.random() * Sum);
	        
	        // find closest weight
	        int distance = Math.abs(Weights[0] - randomGenerated);
	        int idx = 0;
	        for (int i=1; i<Weights.length; i++) {
	        	if (Math.abs(Weights[i] - randomGenerated) < distance) {
	        		distance = Math.abs(Weights[i] - randomGenerated);
	        		idx = i;
	        	}
	        }
	        return idx;
	    }
	}
	
	static class LeetCodeSolution {
		private int[] prefixSums;
		private int totalSum;
		
		public LeetCodeSolution(int[] w) {
		    this.prefixSums = new int[w.length];
		
		    int prefixSum = 0;
		    for (int i = 0; i < w.length; ++i) {
		        prefixSum += w[i];
		        this.prefixSums[i] = prefixSum;
		    }
		    this.totalSum = prefixSum;
		}
		
		public int pickIndex() {
		    double target = this.totalSum * Math.random();
		    int i = 0;
		    // run a linear search to find the target zone
		    for (; i < this.prefixSums.length; ++i) {
		        if (target < this.prefixSums[i])
		            return i;
		    }
		    // to have a return statement, though this should never happen.
		        return i - 1;
		  }
	}
}
