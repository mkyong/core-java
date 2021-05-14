package com.mkyong.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ReadXmlDomParser {

    private static final String FILENAME = "src/main/resources/staff.xml";

    public static void main(String[] args) {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = readXmlFileIntoInputStream("staff.xml")) {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            // read from a path
            //Document doc = db.parse(new File(FILENAME));

            // read from a project's resources folder
            Document doc = db.parse(is);

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("staff");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    String id = element.getAttribute("id");

                    // get text
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String role = element.getElementsByTagName("role").item(0).getTextContent();
                    String bio = element.getElementsByTagName("bio").item(0).getTextContent();

                    NodeList salaryNodeList = element.getElementsByTagName("salary");
                    String salary = salaryNodeList.item(0).getTextContent();

                    // get salary's attribute
                    String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                    System.out.println("Current Element :" + node.getNodeName());
                    System.out.println("Staff Id : " + id);
                    System.out.println("Name : " + name);
                    System.out.println("Role : " + role);
                    System.out.println("Bio : " + bio);
                    System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    // read file from resource's folder.
    private static InputStream readXmlFileIntoInputStream(final String fileName) {
        return ReadXmlDomParser.class.getClassLoader().getResourceAsStream(fileName);
    }

}
