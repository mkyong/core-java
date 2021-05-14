package com.mkyong.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountDepthXmlDom {

    private static final String FILENAME = "src/main/resources/staff.xml";
    private static int DEPTH_XML = 0;

    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(FILENAME)) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            // get all elements
            NodeList childNodes = doc.getChildNodes();

            printNode(childNodes, 0);

            System.out.println("Depth of XML : " + DEPTH_XML);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    // loop recursive
    private static void printNode(NodeList nodeList, int level) {
        level++;

        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    String result = String.format(
                            "%" + level * 5 + "s : [%s]%n", node.getNodeName(), level);
                    System.out.print(result);

                    printNode(node.getChildNodes(), level);

                    // how depth is it?
                    if (level > DEPTH_XML) {
                        DEPTH_XML = level;
                    }

                }

            }
        }

    }

}