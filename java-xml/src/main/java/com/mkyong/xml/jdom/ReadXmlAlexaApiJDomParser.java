package com.mkyong.xml.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.XMLConstants;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ReadXmlAlexaApiJDomParser {

    private static final String REMOTE_URL = "https://data.alexa.com/data?cli=10&url=mkyong.com";

    public static void main(String[] args) {

        try {

            SAXBuilder sax = new SAXBuilder();

            // https://rules.sonarsource.com/java/RSPEC-2755
            // prevent xxe
            sax.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            sax.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            // XML is in a web-based location
            Document doc = sax.build(new URL(REMOTE_URL));

            Element rootNode = doc.getRootElement();
            List<Element> list = rootNode.getChildren("SD");

            for (Element target : list) {

                Element popularity = target.getChild("POPULARITY");

                String url = popularity.getAttributeValue("URL");
                String rank = popularity.getAttributeValue("TEXT");

                System.out.printf("URL : %s%n", url);
                System.out.printf("Alexa Rank : %s%n", rank);

            }

        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }

    }
}
