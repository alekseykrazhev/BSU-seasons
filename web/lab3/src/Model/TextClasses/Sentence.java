package Model.TextClasses;

import java.util.ArrayList;

/**
 * Class Sentence describing part of Text
 */
public class Sentence extends TextPart {

    /**
     * private field - array of parts of sentence
     */
    private final ArrayList<TextPart> sentencesParts;

    /**
     * GetMethod for field sentencesParts
     * @return parts of sentence
     */
    public ArrayList<TextPart> getSentencesParts() {
        return sentencesParts;
    }

    /**
     * Default constructor
     */
    public Sentence() {
        this.sentencesParts = new ArrayList<>();
    }

    /**
     * Constructor with parameter
     */
    public Sentence(ArrayList<TextPart> parts) {
        this.sentencesParts = parts;
    }

    /**
     * Method that add word or punctuation mark to the sentence
     * @param tp - new part of sentence
     */
    public void addSentencePart(TextPart tp) {
        sentencesParts.add(tp);
    }

    /**
     * Method that gets all words in sentence
     * @return array of words
     */
    public ArrayList<Word> getWords(){
        ArrayList<Word> words = new ArrayList<>();
        for(TextPart tp : sentencesParts){
            if(tp.getClass() == Word.class){
                words.add((Word)tp);
            }
        }
        return words;
    }

    /**
     * Method to return marks.
     * @return marks
     */
    public ArrayList<Punctuation> getMarks(){
        ArrayList<Punctuation> punctuations = new ArrayList<>();
        for(TextPart tp : sentencesParts){
            if(tp.getClass() == Punctuation.class){
                punctuations.add((Punctuation) tp);
            }
        }
        return punctuations;
    }

    /**
     * Override method toString()
     * @return sentence
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(TextPart tp : sentencesParts){
            if(!s.toString().equals("") && tp.getClass() != Punctuation.class){
                s.append(" ");
            }
            s.append(tp);
        }
        return s.toString();
    }
}
