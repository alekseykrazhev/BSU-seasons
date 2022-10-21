package Model.TextClasses;

import java.util.ArrayList;

/**
 * Class Text describing part of book
 */
public class Text extends TextPart{
    /**
     * private field array of sentence and parts of code
     */
    private final ArrayList<TextPart> sentCode;

    /**
     * GetMethod for field sentCode
     * @return sent code
     */
    public ArrayList<TextPart> getSentCode(){
        return sentCode;
    }

    /**
     * Default constructor
     */
    public Text() {
        this.sentCode = new ArrayList<>();
    }

    /**
     * Method that get sentences from text
     * @return sentences
     */
    public ArrayList<Sentence> getSentences() {
        ArrayList<Sentence> sentences = new ArrayList<>();
        for (TextPart tp: sentCode) {
            if (tp instanceof Sentence)
                sentences.add((Sentence) tp);
        }
        return sentences;
    }

    /**
     * Method that get parts of code from text
     * @return parts od code
     */
    public ArrayList<Code> getCode() {
        ArrayList<Code> code = new ArrayList<>();
        for (TextPart tp: sentCode) {
            if (tp instanceof Code)
                code.add((Code) tp);
        }
        return code;
    }

    /**
     * Method that add new sentence to text
     * @param sentence - new sentence
     */
    public void addSentence(TextPart sentence) {
        sentCode.add(sentence);
    }

    /**
     * Method that add new part of code to text
     * @param code - new part of code
     */
    public void addCode(TextPart code) {
        sentCode.add(code);
    }

    /**
     * Override method toString()
     * @return text
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(TextPart tp : sentCode){
            s.append(tp);
            s.append(" ");
        }
        return s.toString();
    }
}
