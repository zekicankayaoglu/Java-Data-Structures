import java.util.LinkedHashMap;
import java.util.Map;

public class myMap {

    LinkedHashMap<String, info> map;
    int mapSize = 0;
    String str;

    /**
     * creates a new LinkedHashMap named as map
     */
    public myMap() {
        map = new LinkedHashMap<String, info>();
    }

    /**
     * first deletes all chars other than letter or space then converts all capital
     * letters to lower
     * Also checks the input string is valid or not
     * 
     * @param input the input string
     */
    public void preprocessOperation(String input) {

        this.str = input.replaceAll("[^a-zA-Z\\s]", "");
        this.str = this.str.toLowerCase();
        if (this.str.trim().length() < 1) { // Error handling for wrong input
            System.out.println("\nThe input string is invalid!\n");
        } else {
            System.out.println("Original String: " + input);

            System.out.println("Preprocessed string is: " + str);
            System.out.println();
        }
    }

    /**
     * builds the map according to string
     */
    public void buildMap() {
        String[] words = str.split(" ");
        for (String word : words) {// checks word by word
            for (int i = 0; i < word.length(); i++) {
                char j = word.charAt(i);
                String string = Character.toString(j);
                if (this.mapSize != 0) {
                    if (!this.map.containsKey(string)) {// if does not contain the letter puts it
                        this.map.put(string, new info(word, 1));
                        this.mapSize++;
                    } else {
                        // if it contains the letter it increases the count of info object and add the
                        // word to words
                        info currentInfo = this.map.get(string);
                        currentInfo.increaseCount();
                        currentInfo.addWord(word);
                    }
                } else {
                    this.map.put(string, new info(word, 1));
                    this.mapSize++;
                }
            }
        }
    }

    /**
     * prints the map and in this function I used entry.set but of course I did not
     * use
     * it in merge sort
     */
    public void printMap() {
        if (this.str.trim().length() >= 1) {
            System.out.println("The original (unsorted) map:");
            for (Map.Entry<String, info> it : map.entrySet()) {
                System.out.print("Letter: " + it.getKey() + " - Count: " + it.getValue().count + " - Words: [");
                for (int i = 0; i < it.getValue().words.size(); i++) {
                    if (i == it.getValue().words.size() - 1) {
                        System.out.print(it.getValue().words.get(i) + "]");
                    } else {
                        System.out.print(it.getValue().words.get(i) + ", ");
                    }

                }
                System.out.println();
            }
        }
    }

}