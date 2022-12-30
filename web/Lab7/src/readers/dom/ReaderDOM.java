package readers.dom;

import account.Account;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class ReaderDOM
 */
public class ReaderDOM {

    private static final Logger LOGGER = LogManager.getLogger(ReaderDOM.class);

    /**
     * The method show work of DOM parser
     * @param pathXml - path to XML
     */
    public static void xmlReaderDOM(String pathXml) {

        File xmlFile = new File(pathXml);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            LOGGER.debug("Root element in XML file:" + doc.getDocumentElement().getNodeName());
            System.out.println("\nRoot element :" + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("Account");
            //now XML is loaded as Document in memory, lets convert it to Object List
            List<Account> tbList = new ArrayList<>();

            LOGGER.debug("Now XML is loaded as Document in memory, convert it to account object List.");

            for (int i = 0; i < nodeList.getLength(); i++) {
                tbList.add(getAccount(nodeList.item(i)));
            }

            System.out.println("Account in the XML file by DOM: ");

            for (Account tb : tbList) {
                System.out.println(tb.toString());
            }

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * The method parse elements from XML to account
     * @param node - node
     * @return account object
     */
    private static Account getAccount(Node node) {
        Account account = new Account();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            account.SetAccountNumber(getTagValue("accountNumber", element));
            account.SetAmountMoney(Integer.parseInt(getTagValue("amountMoney", element)));
            account.SetBlocked(Boolean.parseBoolean(getTagValue("blocked", element)));
        }

        return account;
    }

    /**
     *
     * @param tag - tag
     * @param element - element
     * @return node value
     */
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}