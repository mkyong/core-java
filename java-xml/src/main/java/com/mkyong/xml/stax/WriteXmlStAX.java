package com.mkyong.xml.stax;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteXmlStAX {

    public static void main(String[] args) throws XMLStreamException {

        // send the output to a xml file
        try (FileOutputStream out = new FileOutputStream("/home/mkyong/test.xml")) {
            writeXml2(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // send the output to System.out
        // writeXml(System.out);

    }

    // StAX Cursor API
    private static void writeXml(OutputStream out) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        writer.writeStartDocument("utf-8", "1.0");

        // <company>
        writer.writeStartElement("company");

        // <staff>
        // add XML comment
        writer.writeComment("This is Staff 1001");

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
        // </company>

        writer.flush();

        writer.close();

    }

    // StAX Iterator API
    private static void writeXml2(OutputStream out) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        // StAX Iterator API
        XMLEventWriter writer = output.createXMLEventWriter(out);

        XMLEvent event = eventFactory.createStartDocument();
        // default
        //event = eventFactory.createStartDocument("utf-8", "1.0");
        writer.add(event);

        writer.add(eventFactory.createStartElement("", "", "company"));

        // write XML comment
        writer.add(eventFactory.createComment("This is staff 1001"));

        writer.add(eventFactory.createStartElement("", "", "staff"));
        // write XML attribute
        writer.add(eventFactory.createAttribute("id", "1001"));

        writer.add(eventFactory.createStartElement("", "", "name"));
        writer.add(eventFactory.createCharacters("mkyong"));
        writer.add(eventFactory.createEndElement("", "", "name"));

        writer.add(eventFactory.createStartElement("", "", "salary"));
        writer.add(eventFactory.createAttribute("currency", "USD"));
        writer.add(eventFactory.createCharacters("5000"));
        writer.add(eventFactory.createEndElement("", "", "salary"));

        writer.add(eventFactory.createStartElement("", "", "bio"));
        // write XML CData
        writer.add(eventFactory.createCData("HTML tag <code>testing</code>"));
        writer.add(eventFactory.createEndElement("", "", "bio"));

        // </staff>
        writer.add(eventFactory.createEndElement("", "", "staff"));

        // next staff, tired to write more
        // writer.add(eventFactory.createStartElement("", "", "staff"));
        // writer.add(eventFactory.createAttribute("id", "1002"));
        // writer.add(eventFactory.createEndElement("", "", "staff"));

        // end here.
        writer.add(eventFactory.createEndDocument());

        writer.flush();

        writer.close();

    }

}