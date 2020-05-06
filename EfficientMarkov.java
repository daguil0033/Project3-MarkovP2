import java.util.*;

/**
 * Efficient class for Markov text generation. This version
 * rescans the training text for each randomly-generated
 * character. This makes generating N characters an O(T*N)
 * task where T is the size of the training text
 *
 * This class is designed to be extended with state
 * protected rather than private
 *
 * @author Daniel Aguilar
 *
 */

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;

	/**
	 * Construct a BaseMarkov object with
	 * the specified order
	 * super(order) used to set order
	 */
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}

	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}

	/**
	 * this method sets the training text to paramater text
	 * creates new map myMap by using .clear()
	 * checks to see if map contains a key if not it puts empty string arraylist as value
	 * chechsk to see if key is followed by the last elements, if so adds PSEUDO_EOS
	 */
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		String s = "";
		ArrayList<String> alist;
		for (int j = 0; j <= (text.length() - myOrder); j++) {
			s = text.substring(j, j + myOrder);
			if (myMap.containsKey(s)) {
				alist = myMap.get(s);
			} else {
				alist = new ArrayList<String>();
			}
			if (j == text.length()-myOrder) {
				alist.add(PSEUDO_EOS);
			} else {
				alist.add(text.substring(j+myOrder, j+myOrder+1));
			}
			myMap.put(s, alist);
		}
	}

	/**
	 * returns everything that follows parameter key by calling .get() on myMap
	 */
	@Override
	public ArrayList<String> getFollows(String key) {
		if (!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		} else {
			return myMap.get(key);
		}
	}
}
