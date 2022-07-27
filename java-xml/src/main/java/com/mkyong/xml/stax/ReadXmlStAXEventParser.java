package com.mkyong.xml.stax;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadXmlStAXEventParser {

    private static final String FILENAME = "src/main/resources/staff.xml";

    public static void main(String[] args) {

        try {

            printXmlByXmlEventReader(Paths.get(FILENAME));

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

    }

    private static void printXmlByXmlEventReader(Path path)
            throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        // https://rules.sonarsource.com/java/RSPEC-2755
        // prevent xxe
        xmlInputFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        xmlInputFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        XMLEventReader reader = xmlInputFactory.createXMLEventReader(
                new FileInputStream(path.toFile()));

        // event iterator
        while (reader.hasNext()) {

            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {

                StartElement element = event.asStartElement();

                switch (element.getName().getLocalPart()) {
                    // if <staff>
                    case "staff":
                        // id='1001'
                        Attribute id = element.getAttributeByName(new QName("id"));
                        System.out.printf("Staff id : %s%n", id.getValue());
                        break;
                    case "name":
                        // throws StartElementEvent cannot be cast to class javax.xml.stream.events.Characters
                        // element.asCharacters().getData()

                        // this is still '<name>' tag, need move to next event for the character data
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            System.out.printf("Name : %s%n", event.asCharacters().getData());
                        }
                        break;
                    case "role":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            System.out.printf("Role : %s%n", event.asCharacters().getData());
                        }
                        break;
                    case "salary":
                        // currency='USD'
                        Attribute currency = element.getAttributeByName(new QName("currency"));
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            String salary = event.asCharacters().getData();
                            System.out.printf("Salary [Currency] : %,.2f [%s]%n", Float.parseFloat(salary), currency);
                        }
                        break;
                    case "bio":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            // CDATA, no problem.
                            System.out.printf("Bio : %s%n", event.asCharacters().getData());
                        }
                        break;
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                // if </staff>
                if (endElement.getName().getLocalPart().equals("staff")) {
                    System.out.printf("%n%s%n%n", "---");
                }
            }

        }

    }

}