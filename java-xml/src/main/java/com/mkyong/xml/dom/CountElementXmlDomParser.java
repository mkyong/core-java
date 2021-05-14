package com.mkyong.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountElementXmlDomParser {

    private static final String FILENAME = "src/main/resources/staff.xml";

    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(FILENAME)) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            // get all elements known as "staff"
            NodeList list = doc.getElementsByTagName("staff");

            System.out.println("Number of staff elements : " + list.getLength());

            // xpath
            /*XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nodes = (NodeList)
                    xpath.evaluate("//staff", doc, XPathConstants.NODESET);
            int count = nodes.getLength();

            System.out.println("Number of staff elements : " + count);*/

        } catch (ParserConfigurationException | SAXException | IOException /*| XPathExpressionException*/ e) {
            e.printStackTrace();
        }

    }

}
