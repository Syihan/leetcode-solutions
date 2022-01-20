import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Q019InsertDeleteGetRandomO1 {
	
	public static void main(String args[]) {
		
		RandomizedSet solution = new RandomizedSet();
		LeetCodeRandomizedSet leetCodeSolution = new LeetCodeRandomizedSet();
	}

	static class RandomizedSet {
	    ArrayList<Integer> RandomizedSet;
	    Random Rand;

	    public RandomizedSet() {
	        RandomizedSet = new ArrayList<Integer>();
	        Rand = new Random();
	    }

	    public boolean insert(int val) {
	        if (!RandomizedSet.contains(val)) {
	            RandomizedSet.add(val);
	            return true;
	        }
	        return false;
	    }

	    public boolean remove(int val) {
	        if (RandomizedSet.contains(val)) {
	            RandomizedSet.remove(RandomizedSet.indexOf(val));
	            return true;
	        }
	        return false;
	    }

	    public int getRandom() {
	        int randomIndex = Rand.nextInt(RandomizedSet.size());
	        return RandomizedSet.get(randomIndex);
	    }

	    /**
	     * Your RandomizedSet object will be instantiated and called as such:
	     * RandomizedSet obj = new RandomizedSet();
	     * boolean param_1 = obj.insert(val);
	     * boolean param_2 = obj.remove(val);
	     * int param_3 = obj.getRandom();
	     */
	}
	
	static class LeetCodeRandomizedSet {
		  Map<Integer, Integer> dict;
		  List<Integer> list;
		  Random rand = new Random();

		  /** Initialize your data structure here. */
		  public LeetCodeRandomizedSet() {
		    dict = new HashMap<Integer, Integer>();
		    list = new ArrayList<Integer>();
		  }

		  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
		  public boolean insert(int val) {
		    if (dict.containsKey(val)) return false;

		    dict.put(val, list.size());
		    list.add(list.size(), val);
		    return true;
		  }

		  /** Removes a value from the set. Returns true if the set contained the specified element. */
		  public boolean remove(int val) {
		    if (! dict.containsKey(val)) return false;

		    // move the last element to the place idx of the element to delete
		    int lastElement = list.get(list.size() - 1);
		    int idx = dict.get(val);
		    list.set(idx, lastElement);
		    dict.put(lastElement, idx);
		    // delete the last element
		    list.remove(list.size() - 1);
		    dict.remove(val);
		    return true;
		  }

		  /** Get a random element from the set. */
		  public int getRandom() {
		    return list.get(rand.nextInt(list.size()));
		  }
	}
	
}
