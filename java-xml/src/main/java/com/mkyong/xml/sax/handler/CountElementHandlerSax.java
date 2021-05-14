package com.mkyong.xml.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CountElementHandlerSax extends DefaultHandler {

    private final String elementName;
    private Integer count = 0;

    public String getElementName() {
        return elementName;
    }

    public Integer getCount() {
        return count;
    }

    public CountElementHandlerSax(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase(getElementName())) {
            count++;
        }
    }

}
