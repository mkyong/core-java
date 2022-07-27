package com.mkyong.xml.jdom;

import org.jdom2.CDATA;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

public class WriteXmlJDom {

    public static void main(String[] args) throws JDOMException, IOException {

        //writeSimpleXml();

        writeXml(System.out);
        /**
         * <?xml version="1.0" encoding="UTF-8"?>
         * <company>
         *   <staff id="1001">
         *     <name>mkyong</name>
         *     <role>support</role>
         *     <salary curreny="USD">5000</salary>
         *     <!--for special characters like < &, need CDATA-->
         *     <bio><![CDATA[HTML tag <code>testing</code>]]></bio>
         *   </staff>
         *   <staff id="1002">
         *     <name>yflow</name>
         *     <role>admin</role>
         *     <salary curreny="EUD">8000</salary>
         *     <bio><![CDATA[a & b]]></bio>
         *   </staff>
         * </company>
         */

    }

    private static void writeXml(OutputStream output) throws IOException {

        Document doc = new Document();
        doc.setRootElement(new Element("company"));

        Element staff = new Element("staff");
        // add xml attribute
        staff.setAttribute("id", "1001");

        staff.addContent(new Element("name").setText("mkyong"));
        staff.addContent(new Element("role").setText("support"));
        staff.addContent(new Element("salary")
                .setAttribute("curreny", "USD").setText("5000"));

        // add xml comments
        staff.addContent(new Comment("for special characters like < &, need CDATA"));

        // add xml CDATA
        staff.addContent(new Element("bio")
                .setContent(new CDATA("HTML tag <code>testing</code>")));

        // append child to root
        doc.getRootElement().addContent(staff);

        Element staff2 = new Element("staff");
        staff2.setAttribute("id", "1002");
        staff2.addContent(new Element("name").setText("yflow"));
        staff2.addContent(new Element("role").setText("admin"));
        staff2.addContent(new Element("salary")
                .setAttribute("curreny", "EUD").setText("8000"));
        // add xml CDATA
        staff2.addContent(new Element("bio")
                .setContent(new CDATA("a & b")));

        // append child to root
        doc.getRootElement().addContent(staff2);

        XMLOutputter xmlOutputter = new XMLOutputter();
        // change xml encoding
        xmlOutputter.setFormat(Format.getPrettyFormat().setEncoding("ISO-8859-1"));
        //xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(doc, output);

        // output to any OutputStream
        /*try(FileOutputStream fileOutputStream =
                    new FileOutputStream("c:\\test\\file.xml")){
            xmlOutputter.output(doc, fileOutputStream);
        }*/

        // output to any Writer
        /*try(FileWriter fileWriter =
                    new FileWriter("c:\\test\\file.xml")){
            xmlOutputter.output(doc, fileWriter);
        }*/

    }

    private static void writeSimpleXml() throws JDOMException, IOException {

        String xml = "<root><child id=\"100\">mkyong</child></root>";
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(new StringReader(xml));

        //XMLOutputter xmlOutputter = new XMLOutputter();

        // pretty print
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

        // output to console
        xmlOutputter.output(doc, System.out);

    }

}