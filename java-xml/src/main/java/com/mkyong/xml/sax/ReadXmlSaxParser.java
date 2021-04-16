package com.mkyong.xml.sax;

import com.mkyong.xml.sax.handler.PrintAllHandlerSax;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// https://mkyong.com/java/how-to-read-xml-file-in-java-sax-parser/
public class ReadXmlSaxParser {

    private static final String FILENAME = "src/main/resources/staff.xml";
    //private static final String FILENAME = "c://test//staff.xml";

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser saxParser = factory.newSAXParser();

            PrintAllHandlerSax handler = new PrintAllHandlerSax();
            saxParser.parse(FILENAME, handler);

            /*XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);

            InputSource source = new InputSource(FILENAME);
            source.setEncoding(StandardCharsets.UTF_8.displayName());
            xmlReader.parse(source);*/

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}
