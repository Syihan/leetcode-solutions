import java.util.HashMap;

/*
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 * 
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * 
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 * 
 * Constraints:
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 *  */

public class Q015MeetingRoomsII {
	
	public static void main(String[] args) {
		int[][] intervals1 = {{0,30},{5,10},{15,20}};
		System.out.println(minMeetingRooms(intervals1));
		
		int[][] intervals2 = {{7,10},{2,4}};
		System.out.println(minMeetingRooms(intervals2));
		
		int[][] intervals3 = {{6,15},{13,20},{6,17}};
		System.out.println(minMeetingRooms(intervals3));
	}
	
    public static int minMeetingRooms(int[][] intervals) {
    	HashMap<int[], Integer> count = new HashMap<int[], Integer>();
    	
    	int minMeetingRooms = 0;
    	// logic: find where the most of interval overlaps happen
    	for (int i=0; i<intervals.length-1; i++) {
    		for (int j=i+1; j<intervals.length; j++) {
    			if (isOverlapping(intervals[i], intervals[j])) {
        			int[] overlap = getOverlap(intervals[i], intervals[i+1]);
            		count.put(overlap, count.getOrDefault(overlap, 0) + 1);
            		minMeetingRooms = Math.max(minMeetingRooms, count.get(overlap));
            	}
    		}
    	}
    	
        return minMeetingRooms + 1;
    }
    
    public static int[] getOverlap(int[] interval1, int[] interval2) {
    	int[] overlap = new int[2];
    	overlap[0] = Math.max(interval1[0], interval2[0]);
		overlap[1] = Math.min(interval1[1], interval2[1]);
    	return overlap;
    }
    
    public static boolean isOverlapping(int[] interval1, int[] interval2) {
    	int firstDifference = interval1[0] - interval2[0];
    	int secondDifference = interval1[1] - interval2[1];
    	
    	if (firstDifference == 0 || secondDifference == 0) return true;
    	else if ((firstDifference < 0 && secondDifference < 0) || (firstDifference > 0 && secondDifference > 0)) return false;
    	else return true;
    }
}
