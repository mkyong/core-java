package com.mkyong.xml.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;

public class ReadXmlStringJDomParser {

    public static void main(String[] args) {

        String XML = "<root><url>mkyong</url></root>";

        try {

            SAXBuilder sax = new SAXBuilder();
            // String that contains XML
            Document doc = sax.build(new StringReader(XML));

            Element rootNode = doc.getRootElement();
            System.out.println(rootNode.getChildText("url"));

        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }

    }
}
