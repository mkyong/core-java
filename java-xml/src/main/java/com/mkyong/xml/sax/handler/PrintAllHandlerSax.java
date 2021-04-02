package com.mkyong.xml.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

// Scan ampersand characters (&) and left-angle bracket characters (<) and replace them with the strings &amp; or &lt;
public class PrintAllHandlerSax extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();

    @Override
    public void startDocument() {
        System.out.println("Start Document");
    }

    @Override
    public void endDocument() {
        System.out.println("End Document");
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        System.out.printf("Start Element : %s%n", qName);

        if (qName.equalsIgnoreCase("staff")) {
            // get tag's attribute by name
            String id = attributes.getValue("id");
            System.out.printf("Staff id : %s%n", id);
        }

        if (qName.equalsIgnoreCase("salary")) {
            // get tag's attribute by index, 0 = first attribute
            String currency = attributes.getValue(0);
            System.out.printf("Currency :%s%n", currency);
        }

    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {

        System.out.printf("End Element : %s%n", qName);

        if (qName.equalsIgnoreCase("name")) {
            System.out.printf("Name : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("role")) {
            System.out.printf("Role : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("salary")) {
            System.out.printf("Salary : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("bio")) {
            System.out.printf("Bio : %s%n", currentValue.toString());
        }

    }

    // http://www.saxproject.org/apidoc/org/xml/sax/ContentHandler.html#characters%28char%5B%5D,%20int,%20int%29
    // SAX parsers may return all contiguous character data in a single chunk,
    // or they may split it into several chunks
    @Override
    public void characters(char ch[], int start, int length) {

        // The characters() method can be called multiple times for a single text node.
        // Some values may missing if assign to a new string

        // avoid doing this
        // value = new String(ch, start, length);

        // better append it, works for single or multiple calls
        currentValue.append(ch, start, length);

    }

}

