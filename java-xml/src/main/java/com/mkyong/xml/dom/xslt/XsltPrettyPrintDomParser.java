package com.mkyong.xml.dom.xslt;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class XsltPrettyPrintDomParser {

    private static final String XML_FILENAME = "src/main/resources/staff-simple.xml";
    private static final String XSLT_FILENAME = "src/main/resources/xslt/staff-format.xslt";

    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(XML_FILENAME)) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            transform(doc, System.out);


        } catch (IOException | ParserConfigurationException |
                SAXException | TransformerException e) {
            e.printStackTrace();
        }

    }

    private static void transform(Document doc, OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        //Transformer transformer = transformerFactory.newTransformer();

        // add XSLT for pretty print
        Transformer transformer = transformerFactory.newTransformer(
                new StreamSource(new File(XSLT_FILENAME)));

        // pretty print, this will add extra new lines
        // transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // add extra standalone to break the root node to a new line
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        transformer.transform(new DOMSource(doc), new StreamResult(output));

    }

}