package com.mkyong.xml.tips;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Properties;

public class ConvertXmlToPropertiesDom {

    public static void main(String[] args)
            throws IOException, ParserConfigurationException, SAXException {

        Document doc;
        Properties prop = new Properties();

        try (FileInputStream input =
                     new FileInputStream("src/main/resources/staff.xml")) {
            doc = parse(input);
        }

        NodeList list = doc.getElementsByTagName("staff");

        for (int temp = 0; temp < list.getLength(); temp++) {

            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                // get staff's id
                String id = element.getAttribute("id");

                // get text
                String name = element.getElementsByTagName("name")
                        .item(0).getTextContent();

                // write value to properties
                // prop does not guarantee on the order
                prop.setProperty("company.staff" + temp + ".id", id);
                prop.setProperty("company.staff" + temp + ".name", name);

            }

        }

        // write to console for testing
        prop.store(System.out, "");

        // write to a properties file
        /*try (FileOutputStream output =
                     new FileOutputStream("c:\\test\\staff.properties")) {
            prop.store(output, "");
        }*/

    }

    // get document
    private static Document parse(InputStream input)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(input);
        return doc;

    }

}
