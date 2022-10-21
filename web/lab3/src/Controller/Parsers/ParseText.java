package Controller.Parsers;

import Model.TextClasses.Code;
import Model.TextClasses.Text;
import Model.TextClasses.TextPart;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class for parsing text
 */
public class ParseText implements ParseChain{

    /**
     * Logger moment.
     */
    private static final Logger logger = Logger.getLogger( ParseText.class.getName() );

    /**
     * private field parseChain for next parsing
     */
    private ParseChain parseChain;

    /**
     * Override method.
     * @param nextParse parsechain
     */
    @Override
    public void setNextParse(ParseChain nextParse) {
        this.parseChain = nextParse;
    }

    /**
     * Get text part.
     * @param tp - text
     * @return text part.
     */
    @Override
    public TextPart parse(String tp) {
        Text text = new Text();
        Pattern pattern = Pattern.compile("([^.!?<]+[.!?])|(<code>.*?</code>)");
        Matcher matcher = pattern.matcher(tp);
        while (matcher.find())
        {
            if(matcher.group(1) != null) {
                text.addSentence(parseChain.parse(matcher.group(1)));
                logger.info("Sentence is added");
            }
            else{
                text.addCode(new Code(matcher.group(2)));
                logger.info("Part of code is added");
            }
        }
        logger.info("Parsing of text is successful.");
        return text;
    }
}
