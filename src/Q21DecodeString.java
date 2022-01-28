import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q21DecodeString {
	
	public static void main(String[] args) {
		
	}
	
	static class Solution {
		
	    public String decodeString(String s) {
	    	String decodedString = "";
	    	ArrayList<int[]> matches = new ArrayList<int[]>();
	    	ArrayList<Integer> closers = new ArrayList<Integer>();
	    	HashMap<int[], Integer> pairs = new HashMap<int[], Integer>();
	    	
	        // Find all occurrences of an open bracket
	        Pattern pattern = Pattern.compile("[0-9]+\\[");
	        Matcher matcher = pattern.matcher(s);
	        while (matcher.find()) {
	            int[] match = new int[2];
	            match[0] = matcher.start();
	            match[1] = matcher.end();
	            matches.add(match);
	        }
	        
	        // Find all occurrences of a close bracket
	        pattern = Pattern.compile("\\]");
	        matcher = pattern.matcher(s);
	        while (matcher.find()) closers.add(matcher.start());
	        
	        // Iterate through matches and closers to determine sequence
	        for (int[] match : matches) {
	        	int i = matches.indexOf(match);
	        	for (int j=i; j<matches.size(); j++) {
	        		
	        		// if at the end of the '['s, pair the remaining two
	        		if (i == matches.size()-1) pairs.put(match, closers.get(j));
	        		
	        		// if the index of the ']' is greater than the index of the next '[', move on
	        		if (closers.get(j) > matches.get(i+1)[1]) {
	        			j++;
	        		}
	        		// otherwise, pair it
	        		else {
	        			pairs.put(match, closers.get(j));
	        		}
	        	}
	        }
	    	
	    	return decodedString;
	    }
	}
	
	static class LeetCodeSolution {
	    public String decodeString(String s) {
	        Stack<Character> stack = new Stack<>();
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) == ']') {
	                List<Character> decodedString = new ArrayList<>();
	                // get the encoded string
	                while (stack.peek() != '[') {
	                    decodedString.add(stack.pop());
	                }
	                // pop [ from the stack
	                stack.pop();
	                int base = 1;
	                int k = 0;
	                // get the number k
	                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
	                    k = k + (stack.pop() - '0') * base;
	                    base *= 10;
	                }
	                // decode k[decodedString], by pushing decodedString k times into stack
	                while (k != 0) {
	                    for (int j = decodedString.size() - 1; j >= 0; j--) {
	                        stack.push(decodedString.get(j));
	                    }
	                    k--;
	                }
	            }
	            // push the current character to stack
	            else {
	                stack.push(s.charAt(i));
	            }
	        }      
	        // get the result from stack
	        char[] result = new char[stack.size()];
	        for (int i = result.length - 1; i >= 0; i--) {
	            result[i] = stack.pop();
	        }
	        return new String(result);
	    }
	}


}
