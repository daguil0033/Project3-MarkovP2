import java.util.*;

/**
 * Similar to EfficientMarkov, but uses WordGram objects instead of String
 * objects to generate random text.
 *
 * @author Daniel Aguilar
 *
 */

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }

    public EfficientWordMarkov() {
        this(3);
    }

    /**
     * This method builds myMap, its keys and values, using the trainingtext text as parameter
     * loops through the text making each individual word into wordgram key
     * the value of that key is a string arraylist that either find the key or assigned to default empty string arraylist
     * adds PSEUDO_EOS if/when the last word is reached
     * adds to arraylist next and puts it as the value to key key
     */

    public void setTraining(String text) {
        myWords = text.split("\\s+");
        myMap.clear();

        for(int j = 0; j <= (myWords.length - myOrder); j++) {
            WordGram key = new WordGram(myWords, j, myOrder);
            ArrayList<String> next = myMap.getOrDefault(key, new ArrayList<String>());
            if (j + myOrder == myWords.length) {
                next.add(PSEUDO_EOS);
            } else {
                next.add(myWords[j+myOrder]);
            }
            myMap.put(key, next);
        }
    }
    /**
     * this method takes a key and returns what follows it, which would be the value of that key in myMap
     */
    public ArrayList<String> getFollows(WordGram key) {
        if (myMap.get(key) != null) {
            return myMap.get(key);
        } else {
            throw new NoSuchElementException("Element " + key + " is not in the map.");
        }
    }
}
