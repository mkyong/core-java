package com.mkyong.xml.sax.handler;

import com.mkyong.xml.sax.model.Staff;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MapStaffObjectHandlerSax extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    List<Staff> result;
    Staff currentStaff;

    public List<Staff> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        // start of loop
        if (qName.equalsIgnoreCase("staff")) {

            // new staff
            currentStaff = new Staff();

            // staff id
            String id = attributes.getValue("id");
            currentStaff.setId(Long.valueOf(id));
        }

        if (qName.equalsIgnoreCase("salary")) {
            // salary currency
            String currency = attributes.getValue("currency");
            currentStaff.setCurrency(currency);
        }

    }

    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("name")) {
            currentStaff.setName(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("role")) {
            currentStaff.setRole(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("salary")) {
            currentStaff.setSalary(new BigDecimal(currentValue.toString()));
        }

        if (qName.equalsIgnoreCase("bio")) {
            currentStaff.setBio(currentValue.toString());
        }

        // end of loop
        if (qName.equalsIgnoreCase("staff")) {
            result.add(currentStaff);
        }

    }

    // http://www.saxproject.org/apidoc/org/xml/sax/ContentHandler.html#characters%28char%5B%5D,%20int,%20int%29
    // SAX parsers may return all contiguous character data in a single chunk,
    // or they may split it into several chunks
    public void characters(char ch[], int start, int length) {

        // The characters() method can be called multiple times for a single text node.
        // Some values may missing if assign to a new string

        // avoid doing this
        // value = new String(ch, start, length);

        // better append it, works for single or multiple calls
        currentValue.append(ch, start, length);

    }

}

