package com.mkyong.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountDepthXmlDomTreeWalker {

    private static final String FILENAME = "src/main/resources/staff.xml";
    private static int DEPTH_XML = 0;

    public static void main(String[] args) {

        int depth = countDepthXml(FILENAME);
        System.out.println("Depth of XML : " + depth);
    }

    private static int countDepthXml(String filename) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(filename)) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            DocumentTraversal traversal = (DocumentTraversal) doc;

            // DOM tree walker
            TreeWalker walker = traversal.createTreeWalker(
                    doc.getDocumentElement(),
                    NodeFilter.SHOW_ELEMENT,
                    null,
                    true);

            traverseXmlElements(walker, 0);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return DEPTH_XML;

    }

    private static void traverseXmlElements(TreeWalker walker, int level) {

        level++;

        Node node = walker.getCurrentNode();

        String result = String.format(
                "%" + level * 5 + "s : [%s]%n", node.getNodeName(), level);
        System.out.print(result);

        for (Node n = walker.firstChild();
             n != null;
             n = walker.nextSibling()) {
            traverseXmlElements(walker, level);
        }

        walker.setCurrentNode(node);

        // how depth is it?
        if (level > DEPTH_XML) {
            DEPTH_XML = level;
        }

    }

}