package logic;

import readers.dom.ReaderDOM;
import readers.sax.ReaderSAX;
import readers.stax.ReaderStAX;
import static validation.Validation.checkXMLforXSD;

/**
 * Class Logic
 */
public class Logic {
    String pathXml = "accounts.xml";
    String pathXsd = "schema.xsd";
    boolean b;

    /**
     * The method initialize and start xmlReaders
     */
    public void startReaders(){
        ReaderDOM.xmlReaderDOM(pathXml);

        ReaderSAX.xmlReaderSAX(pathXml);

        ReaderStAX.xmlReaderStAX(pathXml);
    }

    /**
     * The method make validation
     * @return boolean b
     */
    public boolean makeValidation() throws Exception{
        return b = checkXMLforXSD(pathXml, pathXsd);
    }
}
