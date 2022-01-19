/*
 * Sometimes people repeat letters to represent extra feeling. For example:
 * 
 * "hello" -> "heeellooo"
 * "hi" -> "hiiii"
 * In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".
 * 
 * You are given a string s and an array of query strings words. A query word is stretchy if it can be made to be equal to s by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is three or more.
 * 
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
 * Return the number of query strings that are stretchy.
 * 
 *  
 *  
 *  Example 1:
 *  
 *  Input: s = "heeellooo", words = ["hello", "hi", "helo"]
 *  Output: 1
 *  Explanation:
 *  We can extend "e" and "o" in the word "hello" to get "heeellooo".
 *  We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *  Example 2:
 *  
 *  Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
 *  Output: 3
 *  
 *  
 *  Constraints:
 *  
 *  1 <= s.length, words.length <= 100
 *  1 <= words[i].length <= 100
 *  s and words[i] consist of lowercase letters.
 */


public class Q016ExpressiveWords {

	public static void main(String[] args) {
		
	}
	
    public static int expressiveWords(String s, String[] words) {
        int nrExpressiveWords = 0;
        
        for(String word : words) {
        	if (isExpressiveWord(s, word)) nrExpressiveWords++;
        }
        
    	return nrExpressiveWords;
    }
    
    public static boolean isExpressiveWord(String s, String word) {
    	
    	int j = 0;
    	int groupSize = 1;
    	char c;
    	
    	for(int i=0; i<s.length()-1; i++) {
    		c = s.charAt(i);
    		if (s.charAt(i+1) == c) groupSize++;
    		else {
    			if (groupSize > 3) {
    				if (word.charAt(j) == c) {
    					if (j == s.length() - 1) return true;
    					else {
    						j++;
    						continue;
    					}
    				}
    				else return false;
    			}
    			else {
    				for (int k = 0; k < groupSize; k++) {
    					if (word.charAt(j) == c) {
    						j++;
    						break;
    					}
    					else return false;
    				}
    				continue;
    			}
    		}
    	}
    	return false;
    }
    
    public int leetCodeExpressiveWords(String S, String[] words) {
        if (S == null || words == null) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            if (leetCodeStretchy(S, word)) {
                count++;
            }
        }
        return count;
    }
    
    public boolean leetCodeStretchy(String S, String word) {
        if (word == null) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < S.length() && j < word.length()) {
            if (S.charAt(i) == word.charAt(j)) {
                int len1 = leetCodeGetRepeatedLength(S, i);
                int len2 = leetCodeGetRepeatedLength(word, j);
                if (len1 < 3 && len1 != len2 || len1 >= 3 && len1 < len2) {
                    return false;
                }
                i += len1;
                j += len2;
            } else {
                return false;
            }
        }
        return i == S.length() && j == word.length();
    }
    
    public int leetCodeGetRepeatedLength(String str, int i) {
        int j = i;
        while (j < str.length() && str.charAt(j) == str.charAt(i)) {
            j++;
        }
        return j - i;
    } 
}
