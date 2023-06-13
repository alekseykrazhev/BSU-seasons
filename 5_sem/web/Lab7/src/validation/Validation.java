package validation;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class Validation
 */
public class Validation {

    private static final Logger LOGGER = LogManager.getLogger(Validation.class);

    /**
     * The method check XML and XSD files for matching.
     * @param pathXml - path to XML
     * @param pathXsd - path to XSD
     */
    public static boolean checkXMLforXSD(String pathXml, String pathXsd) throws Exception {

        try {
            LOGGER.debug("Opening files for validation.");

            File xml = new File(pathXml);
            File xsd = new File(pathXsd);

            if (!xml.exists()) {
                System.out.println("XML not found " + pathXml);
                LOGGER.debug("XML not found " + pathXml);
            }

            if (!xsd.exists()) {
                System.out.println("XSD not found " + pathXsd);
                LOGGER.debug("XSD not found " + pathXsd);
            }

            if (!xml.exists() || !xsd.exists()) {
                return false;
            }

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(pathXsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(pathXml));

            LOGGER.debug("Validation completed, XML matches XSD");
            return true;
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}