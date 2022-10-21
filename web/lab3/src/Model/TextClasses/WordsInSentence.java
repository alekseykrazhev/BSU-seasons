package Model.TextClasses;

/**
 * Class that represents words in sentence.
 */
public class WordsInSentence {
    /**
     * Words in sentence
     */
    private WordsInSentence wordsInSentence;

    /**
     * Word param.
     */
    private Word word;

    /**
     * Number of sentence
     */
    private int numSentence;

    /**
     * Count moment.
     */
    private int count;

    /**
     * Main constructor.
     * @param word - word
     * @param numSentence - num
     * @param count - count
     */
    public WordsInSentence(Word word, int numSentence, int count) {
        this.word = word;
        this.numSentence = numSentence;
        this.count = count;
    }

    /**
     * Constructor
     * @param word - word
     * @param count - count
     */
    public WordsInSentence(Word word, int count) {
        this.word = word;
        this.count = count;
    }

    /**
     * Word getter.
     * @return word
     */
    public Word getWord() {
        return word;
    }

    /**
     * Count getter.
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Out to console
     * @return string
     */
    public String printWordWithCount() {
        return "Word " + word.toString() + " " + count + " times";
    }

    /**
     * ToString override.
     * @return string
     */
    @Override
    public String toString() {
        return "Word " + word.toString() +
                " in " + numSentence + " sentence: " + count;
    }
}
