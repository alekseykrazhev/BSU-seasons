package Model.TextClasses;

/**
 * Class Word describing part of Sentence
 */
public class Word extends TextPart{

    /**
     * private field word
     */
    private String word;

    /**
     * GetMethod for private field word
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     * Constructor with parameter
     * @param word - new value for field word
     */
    public Word(String word) {
        this.word = word;
    }

    /**
     * Override method toString()
     * @return word
     */
    @Override
    public String toString() {
        return word;
    }
}
