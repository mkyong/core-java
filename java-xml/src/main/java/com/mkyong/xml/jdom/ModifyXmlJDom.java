package com.mkyong.xml.jdom;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ModifyXmlJDom {

    private static final String FILENAME = "src/main/resources/staff.xml";

    public static void main(String[] args) throws JDOMException, IOException {

        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File(FILENAME));

        Element rootNode = doc.getRootElement();

        List<Element> listChildrenNode = rootNode.getChildren("staff");

        // staff = 2
        System.out.println("No of child nodes: " + listChildrenNode.size());

        // loop the elements
        for (Element staff : listChildrenNode) {

            String id = staff.getAttribute("id").getValue();

            // if staff id is 1001
            if ("1001".equals(id.trim())) {
                // remove element role
                staff.removeChild("role");
                // get salary element, update attribute from USD to MYR
                staff.getChild("salary").setAttribute("currency", "MYR");
            }

            // if staff id is 1002
            if ("1002".equals(id.trim())) {

                // remove xml element `name`
                staff.removeChild("name");

                // add a new xml element `address` and CDATA content
                staff.addContent(new Element("address")
                        .addContent(new CDATA("123 & abc")));

                // update xml element `salary` to 2000
                staff.getChild("salary").setText("2000");

                // remove the xml element CDATA
                staff.getChild("bio").setText("a & b & c"); // now the & will escape automatically
            }

            // Java 8 to remove all XML comments
            staff.getContent().removeIf(
                    content -> content.getCType() == Content.CType.Comment);

            // remove the XML comments via iterator
            /*ListIterator<Content> iter = staff.getContent().listIterator();
            while (iter.hasNext()) {
                Content content = iter.next();
                if (content.getCType() == Content.CType.Comment) {
                    iter.remove();
                }
            }*/

        }

        // add a new XML child node, staff id 1003
        rootNode.addContent(new Element("staff").setAttribute("id", "1003"));

        // print to console for testing
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(doc, System.out);

    }

}
