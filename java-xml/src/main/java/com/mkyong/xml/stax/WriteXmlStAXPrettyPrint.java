package com.mkyong.xml.stax;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteXmlStAXPrettyPrint {

    public static void main(String[] args) {

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // write XML to ByteArrayOutputStream
            writeXml(out);

            // Java 10
            String xml = out.toString(StandardCharsets.UTF_8);

            // standard way to convert byte array to String
            // String xml = new String(out.toByteArray(), StandardCharsets.UTF_8);
            // System.out.println(formatXML(xml));

            String prettyPrintXML = formatXML(xml);

            // print to console
            System.out.println(prettyPrintXML);

            // Java 11 - write to file
            /*Files.writeString(Paths.get("/home/mkyong/test.xml"),
                    prettyPrintXML, StandardCharsets.UTF_8);*/

            // Java 7 - write to file
            //Files.write(Paths.get("/home/mkyong/test.xml"), prettyPrintXML.getBytes(StandardCharsets.UTF_8));

            // BufferedWriter - write to file
            /*try (FileWriter writer = new FileWriter("/home/mkyong/test.xml");
                 BufferedWriter bw = new BufferedWriter(writer)) {
                bw.write(xml);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        } catch (TransformerException | XMLStreamException /*| IOException*/ e) {
            e.printStackTrace();
        }

        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8)) {

            writeXml(out);

            // Java 10
            String xml = baos.toString(StandardCharsets.UTF_8);
            System.out.println(formatXML(xml));

        } catch (TransformerException e) {
            e.printStackTrace();
        }*/

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

    private static String formatXML(String xml) throws TransformerException {

        // write data to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // alternative
        //Transformer transformer = SAXTransformerFactory.newInstance().newTransformer();

        // pretty print by indention
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // add standalone="yes" or "no", add line break before the root element
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

        /*transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
                "-//W3C//DTD XHTML 1.0 Transitional//EN");

        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");*/

        // output ignore <?xml version="1.0" encoding="UTF-8"?>
        //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));

        return output.toString();

    }

}