package Model.Localization;

import java.util.ListResourceBundle;

/**
 * SetLocale russian language.
 */
public class Localization_ru_RU extends ListResourceBundle{

    /**
     * Changes messages.
     */
    private static final String[][] contents =
            {
                    {"GivenTextIsEmpty","Полученный текст пуст!!!"},
                    {"MaxLength", "Строка с максимальной длиной:"},
                    {"ErrorWithReadingFile", "Ошибка при открытии файла!!!"},
                    {"FileIsEmpty", "Файл пуст!!!"},
                    {"Sentences", "Предложения:"},
                    {"Words", "Слова(1-ое предложение):"},
                    {"Punctuation", "Знаки пунктуации(1-ое предложение):"},
                    {"AllText", "Весь текст:"},
                    {"MyTask", "Мое задание"},
                    {"WordsInSentence", "1.Для каждого слова из заданного списка найти " +
                            "сколько раз оно встречается в каждом предложении"},
                    {"sortWords", "2.вывести слова " +
                            "с общим количеством вхождений"},
                    {"NullArrayParts", "Ни предложений, ни кода нет в тексте!"},
                    {"NoSentences", "Предложений нет в тексте!"}
            };

    /**
     * Get messages.
     * @return messages
     */
    public Object[][] getContents() {
        return contents;
    }
}