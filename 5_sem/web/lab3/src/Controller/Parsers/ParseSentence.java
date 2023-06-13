package Controller.Parsers;

import Model.TextClasses.Punctuation;
import Model.TextClasses.Sentence;
import Model.TextClasses.TextPart;
import Model.TextClasses.Word;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class for parsing sentence
 */
public class ParseSentence implements ParseChain{

    /**
     * Logger moment
     */
    private static final Logger logger = Logger.getLogger( ParseSentence.class.getName() );

    /**
     * Override method
     * @param nextParse - parsechain
     */
    @Override
    public void setNextParse(ParseChain nextParse) { }

    /**
     * Get parsed sentence
     * @param tp - text
     * @return sentence
     */
    @Override
    public TextPart parse(String tp) {
        Sentence sentence = new Sentence();
        Pattern pattern = Pattern.compile("(\\p{Alnum}+)|(\\p{Punct}+)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(tp);
        while (matcher.find())
        {
            if(matcher.group(1) != null) {
                sentence.addSentencePart(new Word(matcher.group(1)));
            }
            else{
                sentence.addSentencePart(new Punctuation(matcher.group(2)));
            }
        }
        logger.info("Parsing of sentence is successful");
        return sentence;
    }
}
