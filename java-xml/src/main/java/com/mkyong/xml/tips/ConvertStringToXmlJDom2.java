package com.mkyong.xml.tips;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.xml.XMLConstants;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

// JDOM2 Parser
public class ConvertStringToXmlJDom2 {

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
        String xml = convertXmlDocumentToString(document);

        System.out.println(xml);

    }

    private static String convertXmlDocumentToString(Document doc) {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        StringWriter result = new StringWriter();
        try {
            xmlOutput.output(doc, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }

    private static Document convertStringToXml(String xmlString) {

        SAXBuilder sax = new SAXBuilder();

        // https://rules.sonarsource.com/java/RSPEC-2755
        // prevent xxe
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        try {

            Document doc = sax.build(new StringReader(xmlString));
            return doc;

        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }

    }

}