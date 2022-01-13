import java.util.HashMap;

public class Q002PairsOfSongsWithTotalDurationsDivisibleBy60 {
	/*
	 * 	You are given a list of songs where the ith song has a duration of time[i] seconds.
		Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
		 
		Example 1:
		Input: time = [30,20,150,100,40]
		Output: 3
		Explanation: Three pairs have a total duration divisible by 60:
		(time[0] = 30, time[2] = 150): total duration 180
		(time[1] = 20, time[3] = 100): total duration 120
		(time[1] = 20, time[4] = 40): total duration 60
		Example 2:
		Input: time = [60,60,60]
		Output: 3
		Explanation: All three pairs have a total duration of 120, which is divisible by 60.
		 
		Constraints:
			• 1 <= time.length <= 6 * 104
			• 1 <= time[i] <= 500
	 */
	
	public static void main(String[] args) {
		int[] time = { 30,20,150,100,40 };
		int result = numPairsDivisibleBy60(time);
		System.out.println(result);
	}
	
	public static int numPairsDivisibleBy60(int[] time) {
        
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int t : time) {
            int x = (60 - t % 60) % 60; // Not sure why this works but it does
            result += map.getOrDefault(x, 0); // update the number of songs that if adding t equals to a multiple of 60.
            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1); // update the number of t % 60
        }
        
        return result;
    }
}
