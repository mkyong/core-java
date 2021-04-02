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

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser saxParser = factory.newSAXParser();

            PrintAllHandlerSax handler = new PrintAllHandlerSax();
            saxParser.parse(FILENAME, handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}
