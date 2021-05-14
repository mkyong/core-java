package com.mkyong.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ReadXmlAlexaApiDomParser {

    private static final String ALEXA_API = "http://data.alexa.com/data?cli=10&url=";
    private final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public static void main(String[] args) {

        ReadXmlAlexaApiDomParser obj = new ReadXmlAlexaApiDomParser();
        int alexaRanking = obj.getAlexaRanking("mkyong.com");

        System.out.println("Ranking: " + alexaRanking);

    }

    public int getAlexaRanking(String domain) {

        int result = 0;

        String url = ALEXA_API + domain;

        try {

            URLConnection conn = new URL(url).openConnection();

            try (InputStream is = conn.getInputStream()) {

                // unknown XML better turn on this
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                DocumentBuilder dBuilder = dbf.newDocumentBuilder();

                Document doc = dBuilder.parse(is);

                Element element = doc.getDocumentElement();

                // find this tag "POPULARITY"
                NodeList nodeList = element.getElementsByTagName("POPULARITY");
                if (nodeList.getLength() > 0) {

                    Element elementAttribute = (Element) nodeList.item(0);
                    String ranking = elementAttribute.getAttribute("TEXT");
                    if (!"".equals(ranking)) {
                        result = Integer.parseInt(ranking);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid request for domain : " + domain);
        }

        return result;
    }

}
