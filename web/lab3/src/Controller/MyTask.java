package Controller;

import Model.TextClasses.Sentence;
import Model.TextClasses.Word;
import Model.TextClasses.WordsInSentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Class to do lab task
 * @author alex
 */
public class MyTask {

    /**
     * Just resource bundle.
     */
    static ResourceBundle bundle;

    /**
     * Put output to console.
     */
    private static final Logger logger = Logger.getLogger( MyTask.class.getName() );

    /**
     * default constructor
     */
    public MyTask(ResourceBundle bundle){
        MyTask.bundle = bundle;
    }

    /**
     * Method that return words in sentence with count
     */
    public ArrayList<WordsInSentence> wordsInSentencesFunction(ArrayList<Sentence> sentences, ArrayList<Word> words) {
        ArrayList<WordsInSentence> wordsInSentences = new ArrayList<>();
        for (Word wordToFind : words) {
            for (Sentence sentence : sentences) {
                ArrayList<Word> sentenceWords = sentence.getWords();
                int counter = 0;
                for (Word word : sentenceWords) {
                    if (word.getWord().equals(wordToFind.getWord())) {
                        counter++;
                    }
                }
                wordsInSentences.add(new WordsInSentence(wordToFind, sentences.indexOf(sentence), counter));
            }
        }

        logger.info("Words in sentences");
        return wordsInSentences;
    }

    /**
     * Method that sort words by count in sentences
     */
    public Map<String, Integer> sortWords(ArrayList<WordsInSentence> wordsInSentences) {
        Map<String, Integer> myMap = new HashMap<>();
        for(int i = 0; i < wordsInSentences.size(); i++) {
            int count = 0;
            for (WordsInSentence wordsInSentence : wordsInSentences) {
                if (wordsInSentence.getWord().toString().equals(wordsInSentences.get(i).getWord().toString())) {
                    count += wordsInSentence.getCount();
                }
            }

            WordsInSentence wordInCurrentSentence = new WordsInSentence(wordsInSentences.get(i).getWord(), count);

            myMap.put(wordInCurrentSentence.getWord().toString(), count);
        }

        logger.info("All words count in sentences");
        return myMap;
    }
}
