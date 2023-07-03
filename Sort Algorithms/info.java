import java.util.ArrayList;

public class info {
    ArrayList<String> words = new ArrayList<>();
    int count = 0;

    /**
     * 
     * @param word  adds to words list
     * @param count keeps the count
     */
    public info(String word, int count) {
        this.count = count;
        this.words.add(word);
    }

    /**
     * adds new word to words
     * 
     * @param newWord
     */
    public void addWord(String newWord) {
        this.words.add(newWord);

    }

    /**
     * increases the count
     */
    public void increaseCount() {
        this.count++;
    }

}
