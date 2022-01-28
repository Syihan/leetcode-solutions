import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Q025BasicCalculatorII {
	
	/*
	 * Given a string s which represents an expression, evaluate this expression and return its value.
	 * 
	 * The integer division should truncate toward zero.
	 * 
	 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
	 * 
	 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
	 * 
	 *  Example 1:
	 *  Input: s = "3+2*2"
	 *  Output: 7
	 *  
	 *  Example 2:
	 *  Input: s = " 3/2 "
	 *  Output: 1
	 *  
	 *  Example 3:
	 *  Input: s = " 3+5 / 2 "
	 *  Output: 5
	 *  
	 *  Constraints:
	 *  1 <= s.length <= 3 * 105
	 *  s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
	 *  s represents a valid expression.
	 *  All the integers in the expression are non-negative integers in the range [0, 231 - 1].
	 *  The answer is guaranteed to fit in a 32-bit integer.
	 *  
	 */
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		LeetCodeSolution leetCodeSolution = new LeetCodeSolution();
		
		String input1 = "3+2*2";
		String input2 = " 3/2 ";
		String input3 = " 3+5 / 2";
		
		System.out.println(solution.calculate(input1));
		System.out.println(solution.calculate(input2));
		System.out.println(solution.calculate(input3));
		
		System.out.println(leetCodeSolution.calculate(input1));
		System.out.println(leetCodeSolution.calculate(input2));
		System.out.println(leetCodeSolution.calculate(input3));
	}
	
	static class Solution {
	    public int calculate(String s) {
	        Deque<Integer> values = new ArrayDeque<Integer>();
	        Deque<Character> operators = new ArrayDeque<Character>();
	        Deque<Integer> tempVals = new ArrayDeque<Integer>();
	        Deque<Character> tempOps = new ArrayDeque<Character>();

	        Boolean multiply = false;
	        Boolean divide = false;
	        for (int i=0; i<s.length(); i++) {
	            String valueStr = "";
	            while (i < s.length() && Character.isDigit(s.charAt(i))) {
	                valueStr += s.charAt(i);
	                i++;
	            }
	            if (valueStr.length() > 0) {
	                int value = Integer.parseInt(valueStr);
	                if(multiply) {
	                    int v2 = value * values.pop();
	                    values.push(v2);
	                    operators.pop();
	                }
	                else if (divide) {
	                    int v2 = values.pop() / value;
	                    values.push(v2);
	                    operators.pop();
	                }
	                else values.push(value);
	                multiply = divide = false;
	                i--;
	                continue;
	            }
	            if (s.charAt(i) == '*') multiply = true;
	            else if (s.charAt(i) == '/') divide = true;
	            else if (s.charAt(i) == ' ') continue;
	            operators.push(s.charAt(i));
	        }
	        
	        for (Integer i : values) tempVals.push(i);
	        for (Character c : operators) tempOps.push(c);
	        int sum = tempVals.pop();
	        while(!tempVals.isEmpty()) {
	            if (tempOps.pop() == '+') sum += tempVals.pop();
	            else sum -= tempVals.pop();
	        }
	        
	        return sum;
	    }
	}
	
	static class LeetCodeSolution {
	    public int calculate(String s) {

	        if (s == null || s.isEmpty()) return 0;
	        int len = s.length();
	        Stack<Integer> stack = new Stack<Integer>();
	        int currentNumber = 0;
	        char operation = '+';
	        for (int i = 0; i < len; i++) {
	            char currentChar = s.charAt(i);
	            if (Character.isDigit(currentChar)) {
	                currentNumber = (currentNumber * 10) + (currentChar - '0');
	            }
	            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
	                if (operation == '-') {
	                    stack.push(-currentNumber);
	                }
	                else if (operation == '+') {
	                    stack.push(currentNumber);
	                }
	                else if (operation == '*') {
	                    stack.push(stack.pop() * currentNumber);
	                }
	                else if (operation == '/') {
	                    stack.push(stack.pop() / currentNumber);
	                }
	                operation = currentChar;
	                currentNumber = 0;
	            }
	        }
	        int result = 0;
	        while (!stack.isEmpty()) {
	            result += stack.pop();
	        }
	        return result;
	    }
	}

}
