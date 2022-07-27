package com.mkyong.xml.sax;

import com.mkyong.xml.sax.handler.PrintAllHandlerSax;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

// https://mkyong.com/java/how-to-read-xml-file-in-java-sax-parser/
public class ReadXmlSaxParser {

    private static final String FILENAME = "src/main/resources/staff.xml";
    //private static final String FILENAME = "c://test//staff.xml";

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            // https://rules.sonarsource.com/java/RSPEC-2755
            // prevent XXE, completely disable DOCTYPE declaration:
            // factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            SAXParser saxParser = factory.newSAXParser();

            PrintAllHandlerSax handler = new PrintAllHandlerSax();

            saxParser.parse(FILENAME, handler);

            /*XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);

            InputSource source = new InputSource(FILENAME);
            source.setEncoding(StandardCharsets.UTF_8.displayName());
            xmlReader.parse(source);*/

            // count elements name known as "staff"
            /*CountElementHandlerSax countStaffHandler = new CountElementHandlerSax("staff");
            saxParser.parse(FILENAME, countStaffHandler);

            System.out.println("Number of staff elements : " + countStaffHandler.getCount());*/

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}