package com.mkyong.xml.stax;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadXmlStAXCursorParser {

    private static final String FILENAME = "src/main/resources/staff.xml";

    public static void main(String[] args) {

        try {

            printXmlByXmlCursorReader(Paths.get(FILENAME));

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

    }

    public final static String getEventTypeString(int eventType) {
        switch (eventType) {
            case XMLEvent.START_ELEMENT:
                return "START_ELEMENT";

            case XMLEvent.END_ELEMENT:
                return "END_ELEMENT";

            case XMLEvent.PROCESSING_INSTRUCTION:
                return "PROCESSING_INSTRUCTION";

            case XMLEvent.CHARACTERS:
                return "CHARACTERS";

            case XMLEvent.COMMENT:
                return "COMMENT";

            case XMLEvent.START_DOCUMENT:
                return "START_DOCUMENT";

            case XMLEvent.END_DOCUMENT:
                return "END_DOCUMENT";

            case XMLEvent.ENTITY_REFERENCE:
                return "ENTITY_REFERENCE";

            case XMLEvent.ATTRIBUTE:
                return "ATTRIBUTE";

            case XMLEvent.DTD:
                return "DTD";

            case XMLEvent.CDATA:
                return "CDATA";

            case XMLEvent.SPACE:
                return "SPACE";
        }
        return "UNKNOWN_EVENT_TYPE , " + eventType;
    }

    private static void printXmlByXmlCursorReader(Path path) throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        //XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path.toFile()));

        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream(path.toFile()));

        int eventType = reader.getEventType();
        System.out.println(eventType);
        System.out.println(reader);

        while(reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                switch (reader.getName().getLocalPart()) {

                    case "staff":
                        String id = reader.getAttributeValue(null, "id");
                        System.out.printf("Staff id : %s%n", id);
                        break;

                }

            }
            String eventTypeString = getEventTypeString(eventType);
            System.out.println(eventTypeString);
        }



        // event iterator
        /*while (reader.hasNext()) {

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

        }*/

    }

}