import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q023GenerateParentheses {
	
	/*
	 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	 * 
	 * Example 1:
	 * Input: n = 3
	 * Output: ["((()))","(()())","(())()","()(())","()()()"]
	 * 
	 * Example 2:
	 * Input: n = 1
	 * Output: ["()"]
	 * 
	 * Constraints:
	 * 1 <= n <= 8
	 */
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		LeetCodeSolution leetCodeSolution = new LeetCodeSolution();
		
		int n1 = 3;
		System.out.println(solution.generateParenthesis(n1));
		System.out.println(leetCodeSolution.generateParenthesis(n1));
	}
	
	static class Solution {
		List<String> parenthesesCombos = new ArrayList<String>();
		
		public List<String> generateParenthesis(int n) {
			char[] s = new char[2*n];
			
			// put open and closing parentheses alternating
			for(int i=0; i<2*n; i+=2) {
				s[i] = '(';
				s[i+1] = ')';
			}
			
			findCombos(s,0);
	        
			return parenthesesCombos;
	    }
		
		public void findCombos(char[] s, int index) {
			char[] newS = new char[s.length];
			for(int i=0; i<s.length; i++) {
				newS[i] = s[i];
			}
			
			for(int i=index; i<newS.length-1; i++) {
				if(newS[i] == ')' && newS[i+1]=='(') {
					 newS[i] = '(';
					 newS[i+1] = ')';
					 String combo = new String(newS);
					 if(!parenthesesCombos.contains(combo)) parenthesesCombos.add(combo);
					 findCombos(newS, i);
				}
			}
		}
	}
	
	static class LeetCodeSolution {
	    public List<String> generateParenthesis(int n) {
	        List<String> combinations = new ArrayList();
	        generateAll(new char[2 * n], 0, combinations);
	        return combinations;
	    }

	    public void generateAll(char[] current, int pos, List<String> result) {
	        if (pos == current.length) {
	            if (valid(current))
	                result.add(new String(current));
	        } else {
	            current[pos] = '(';
	            generateAll(current, pos+1, result);
	            current[pos] = ')';
	            generateAll(current, pos+1, result);
	        }
	    }

	    public boolean valid(char[] current) {
	        int balance = 0;
	        for (char c: current) {
	            if (c == '(') balance++;
	            else balance--;
	            if (balance < 0) return false;
	        }
	        return (balance == 0);
	    }
	}
}
