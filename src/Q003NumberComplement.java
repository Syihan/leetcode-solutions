
public class Q003NumberComplement {
	/*
	 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
	 *
	 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
	 * Given an integer num, return its complement.
	 * 
	 * Example 1:
	 * Input: num = 5
	 * Output: 2
	 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
	 * 
	 * Example 2:
	 * Input: num = 1
	 * Output: 0
	 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
	 * 
	 * Constraints:
	 * 1 <= num < 231
	 */
	
	public static void main(String[] args) {
		
	}
	
	public static int bitwiseComplement(int n) {
        String result = Integer.toBinaryString(n);
        String reverse = "";
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '0') reverse += '1';
            else reverse += '0';
        }
        int val = Integer.parseInt(reverse, 2);
        return val;
    }
	
	public int leetCodeBitwiseComplement(int N) {
	    if (N == 0) return 1;
	    // bitmask has the same length as N and contains only ones 1...1
	    int bitmask = N;
	    bitmask |= (bitmask >> 1);
	    bitmask |= (bitmask >> 2);
	    bitmask |= (bitmask >> 4);
	    bitmask |= (bitmask >> 8);
	    bitmask |= (bitmask >> 16);
	    // flip all bits 
	    return bitmask ^ N;
	  }

}
