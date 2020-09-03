package com.mkyong.xml.sax;

import com.mkyong.xml.Staff;
import com.mkyong.xml.sax.handler.SAXStaffContentHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// https://mkyong.com/java/how-to-read-xml-file-in-java-sax-parser/
public class SAXReadXMLParser {

    // get XML file from resources folder.
    private static InputStream getXMLFileAsStream() {
        return SAXReadXMLParser.class.getClassLoader().getResourceAsStream("file.xml");
    }

    private void parseXML() {

        try (InputStream xmlFileAsStream = getXMLFileAsStream()) {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            /*XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new SAXPrintAllContentHandler());
            xmlReader.parse();*/

            // Example 1 - Print all xml tags
            //saxParser.parse(xmlFileAsStream, new SAXPrintAllContentHandler());

            // Example 2 - parse XML and bind to object, it works, but not recommend, try JAXB
            SAXStaffContentHandler handle = new SAXStaffContentHandler();
            saxParser.parse(xmlFileAsStream, handle);

            List<Staff> result = handle.getResult();
            result.forEach(x -> System.out.println(x));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SAXReadXMLParser obj = new SAXReadXMLParser();
        obj.parseXML();
    }

}
