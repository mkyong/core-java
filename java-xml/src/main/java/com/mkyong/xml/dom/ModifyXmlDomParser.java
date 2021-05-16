package com.mkyong.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ModifyXmlDomParser {

    private static final String FILENAME = "src/main/resources/staff-simple.xml";
    // xslt for pretty print only, no special task
    private static final String FORMAT_XSLT = "src/main/resources/xslt/staff-format.xslt";

    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(FILENAME)) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            NodeList listOfStaff = doc.getElementsByTagName("staff");
            //System.out.println(listOfStaff.getLength()); // 2

            for (int i = 0; i < listOfStaff.getLength(); i++) {
                // get first staff
                Node staff = listOfStaff.item(i);
                if (staff.getNodeType() == Node.ELEMENT_NODE) {
                    String id = staff.getAttributes().getNamedItem("id").getTextContent();
                    if ("1001".equals(id.trim())) {

                        NodeList childNodes = staff.getChildNodes();

                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node item = childNodes.item(j);
                            if (item.getNodeType() == Node.ELEMENT_NODE) {

                                if ("role".equalsIgnoreCase(item.getNodeName())) {
                                    // update xml element `role` text
                                    item.setTextContent("founder");
                                }
                                if ("name".equalsIgnoreCase(item.getNodeName())) {
                                    // remove xml element `name`
                                    staff.removeChild(item);
                                }
                            }

                        }

                        // add a new xml element, address
                        Element address = doc.createElement("address");
                        // add a new xml CDATA
                        CDATASection cdataSection =
                                doc.createCDATASection("HTML tag <code>testing</code>");

                        address.appendChild(cdataSection);

                        staff.appendChild(address);

                    }

                    if ("1002".equals(id.trim())) {

                        // update xml attribute, from 1002 to 2222
                        staff.getAttributes().getNamedItem("id").setTextContent("2222");

                        // add a new xml element, salary
                        Element salary = doc.createElement("salary");
                        salary.setAttribute("currency", "USD");
                        salary.appendChild(doc.createTextNode("1000"));
                        staff.appendChild(salary);

                        // rename a xml element from `name` to `n`
                        // sorry, no API for this, we need to remove and create
                        NodeList childNodes = staff.getChildNodes();

                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node item = childNodes.item(j);
                            if (item.getNodeType() == Node.ELEMENT_NODE) {

                                if ("name".equalsIgnoreCase(item.getNodeName())) {

                                    // Get the text of element `name`
                                    String name = item.getTextContent();

                                    // remove xml element `name`
                                    staff.removeChild(item);

                                    // add a new xml element, n
                                    Element n = doc.createElement("n");
                                    n.appendChild(doc.createTextNode(name));

                                    // add a new comment
                                    Comment comment = doc.createComment("from name to n");
                                    staff.appendChild(comment);

                                    staff.appendChild(n);

                                }
                            }

                        }

                    }

                }

            }

            // output to console
            // writeXml(doc, System.out);

            try (FileOutputStream output =
                         new FileOutputStream("c:\\test\\staff-modified.xml")) {
                writeXml(doc, output);
            }

        } catch (ParserConfigurationException | SAXException
                | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }

    // write doc to output stream
    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // The default add many empty new line, not sure why?
        // https://stackoverflow.com/questions/58478632/how-to-avoid-extra-blank-lines-in-xml-generation-with-java
        // https://mkyong.com/java/pretty-print-xml-with-java-dom-and-xslt/
        // Transformer transformer = transformerFactory.newTransformer();

        // add a xslt to remove the extra newlines
        Transformer transformer = transformerFactory.newTransformer(
                new StreamSource(new File(FORMAT_XSLT)));

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

}
