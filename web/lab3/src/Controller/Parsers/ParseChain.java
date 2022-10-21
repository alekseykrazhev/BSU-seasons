package Controller.Parsers;

import Model.TextClasses.TextPart;

/**
 * Interface for realisation of parsing text
 */
public interface ParseChain {

    /**
     * Method for next chains in parsing
     * @param nextParse - parse
     */
    void setNextParse(ParseChain nextParse);

    /**
     * Method of parsing string(text)
     * @param tp - text
     * @return divided part of text
     */
    TextPart parse(String tp);
}
