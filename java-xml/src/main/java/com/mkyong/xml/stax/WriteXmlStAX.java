package com.mkyong.xml.stax;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class WriteXmlStAX {

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream("test.xml"));

        writer.writeStartDocument();
        writer.setPrefix("c", "http://c");
        writer.setDefaultNamespace("http://c");

        writer.writeStartElement("http://c", "a");
        writer.writeAttribute("b", "blah");
        writer.writeNamespace("c", "http://c");
        writer.writeDefaultNamespace("http://c");

        writer.setPrefix("d", "http://c");
        writer.writeEmptyElement("http://c", "d");
        writer.writeAttribute("http://c", "chris", "fry");
        writer.writeNamespace("d", "http://c");
        writer.writeCharacters("Jean Arp");
        writer.writeEndElement();

        writer.flush();

    }
}
