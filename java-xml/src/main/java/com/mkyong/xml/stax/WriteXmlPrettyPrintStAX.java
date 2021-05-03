package com.mkyong.xml.stax;

//import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class WriteXmlPrettyPrintStAX {

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException, TransformerException {

        //FileOutputStream fileOutputStream = new FileOutputStream("test.xml");

        StringWriter out = new StringWriter();
        writeXml(out);
        // raw XML
        System.out.println(out);

        // pretty print XML
        String formattedXML = formatXML(out.toString());
        System.out.println(formattedXML);

    }

    private static void writeXml(Writer out) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        //XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream("test.xml"));
        //XMLStreamWriter writer = output.createXMLStreamWriter(stream);
        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        // needs third party stax-utils library
        // writer = new IndentingXMLStreamWriter(writer);

        writer.writeStartDocument("utf-8", "1.0");

        writer.writeStartElement("Company");

        // <staff>
        writer.writeStartElement("staff");
        writer.writeAttribute("id", "1001");

        writer.writeStartElement("name");
        writer.writeCharacters("mkyong");
        writer.writeEndElement();

        writer.writeStartElement("salary");
        writer.writeAttribute("currency", "USD");
        writer.writeCharacters("5000");
        writer.writeEndElement();

        writer.writeStartElement("bio");
        writer.writeCData("HTML tag <code>testing</code>");
        writer.writeEndElement();

        writer.writeEndElement();
        // </staff>

        // <staff>
        writer.writeStartElement("staff");
        writer.writeAttribute("id", "1002");

        writer.writeStartElement("name");
        writer.writeCharacters("yflow");
        writer.writeEndElement();

        writer.writeStartElement("salary");
        writer.writeAttribute("currency", "EUR");
        writer.writeCharacters("8000");
        writer.writeEndElement();

        writer.writeStartElement("bio");
        writer.writeCData("a & b");
        writer.writeEndElement();

        writer.writeEndElement();
        // </staff>

        writer.writeEndDocument();

        writer.flush();

        writer.close();

    }

    private static String formatXML(String xml) throws TransformerException {

        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));

        StringWriter output = new StringWriter();
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

        return output.toString();

        // Output to console for testing
        //StreamResult result = new StreamResult(System.out);

        /*Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Writer out = new StringWriter();
        t.transform(new StreamSource(new StringReader(xml)), new StreamResult(out));
        return out.toString();*/

    }
}
