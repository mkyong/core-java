package com.mkyong.xml.tips;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

// DOM Parser
public class ConvertStringToXmlDom {

    final static String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<company>\n" +
            "    <staff id=\"1001\">\n" +
            "        <name>mkyong</name>\n" +
            "        <role>support</role>\n" +
            "        <salary currency=\"USD\">5000</salary>\n" +
            "        <!-- for special characters like < &, need CDATA -->\n" +
            "        <bio><![CDATA[HTML tag <code>testing</code>]]></bio>\n" +
            "    </staff>\n" +
            "    <staff id=\"1002\">\n" +
            "        <name>yflow</name>\n" +
            "        <role>admin</role>\n" +
            "        <salary currency=\"EUR\">8000</salary>\n" +
            "        <bio><![CDATA[a & b]]></bio>\n" +
            "    </staff>\n" +
            "</company>";

    public static void main(String[] args) {

        // String to XML Document
        Document document = convertStringToXml(str);

        // XML Document to String
        String xml = convertXmlToString(document);

        System.out.println(xml);

    }

    private static String convertXmlToString(Document doc) {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    private static Document convertStringToXml(String xmlString) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

            return doc;

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

}