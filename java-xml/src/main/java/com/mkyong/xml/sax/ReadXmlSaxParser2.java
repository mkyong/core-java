package com.mkyong.xml.sax;

import com.mkyong.xml.sax.handler.MapStaffObjectHandlerSax;
import com.mkyong.xml.sax.model.Staff;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReadXmlSaxParser2 {

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = getXMLFileAsStream()) {

            SAXParser saxParser = factory.newSAXParser();

            // parse XML and map to object, it works, but not recommend, try JAXB
            MapStaffObjectHandlerSax handler = new MapStaffObjectHandlerSax();

            // try XMLReader
            //saxParser.parse(is, handler);

            // more options for configuration
            XMLReader xmlReader = saxParser.getXMLReader();
            //xmlReader.setErrorHandler(new CustomErrorHandlerSax(System.err));
            xmlReader.setContentHandler(handler);

            InputSource source = new InputSource(is);
            // set encoding
            // source.setEncoding(StandardCharsets.UTF_8.toString());

            // The XML encoding is utf-8, here is utf-16, throws below error
            // Fatal Error: URI=null Line=1: Content is not allowed in prolog.
            //source.setEncoding(StandardCharsets.UTF_16.toString());
            xmlReader.parse(source);

            // print all
            List<Staff> result = handler.getResult();
            result.forEach(System.out::println);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    // get XML file from resources folder.
    private static InputStream getXMLFileAsStream() {
        return ReadXmlSaxParser2.class.getClassLoader().getResourceAsStream("staff.xml");
    }

}
