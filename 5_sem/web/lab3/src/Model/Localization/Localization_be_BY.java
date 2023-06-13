package Model.Localization;

import java.util.ListResourceBundle;

/**
 * SetLocale belarusian lang.
 */
public class Localization_be_BY extends ListResourceBundle{

    /**
     * Set messages.
     */
    private static final String[][] contents =
            {
                    {"GivenTextIsEmpty","Атрыманы тэкст пусты!!!"},
                    {"MaxLength", "Радок з максімальнай даўжынёй:"},
                    {"ErrorWithReadingFile", "Памылка пры адкрыцці файла!!!"},
                    {"FileIsEmpty", "Файл пусты!!!"},
                    {"Sentences", "Сказы:"},
                    {"Words", "Словы(1-ы сказ):"},
                    {"Punctuation", "Знакі пунктуацыі(1-ы сказ):"},
                    {"AllText", "Увесь тэкст:"},
                    {"MyTask", "МАЁ ЗАДАННЕ"},
                    {"WordsInSentence", "1.Для кожнага словы з зададзенага спісу " +
                            "знайсці колькі разоў яно сустракаецца ў кожным сказе"},
                    {"sortWords", "2.вывесці словы з агульнай колькасцю уваходжанняў"},
                    {"NullArrayParts", "Ні сказаў, ні коду няма ў тэксте!"},
                    {"NoSentences", "Сказаў няма ў тэксте!"}
            };

    /**
     * Get messages
     * @return messages
     */
    public Object[][] getContents() {
        return contents;
    }
}