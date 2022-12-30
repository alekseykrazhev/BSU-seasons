package readers.sax;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import account.Account;
import org.xml.sax.SAXException;
import account.Account;

/**
 * Class ReaderSAX
 */
public class ReaderSAX {

    /**
     * The method show work of SAX parser with our handler
     * @param pathXml - path to XML
     */
    public static void xmlReaderSAX(String pathXml) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();

            saxParser.parse(new File(pathXml), handler);

            List<Account> accList = handler.getAccList();

            System.out.println("\nAccounts in the XML file by SAX:");

            for(Account acc : accList)
                System.out.println(acc);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}