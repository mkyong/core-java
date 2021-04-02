package com.mkyong.xml.sax.handler;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.PrintStream;

public class CustomErrorHandlerSax implements ErrorHandler {

    private PrintStream out;

    public CustomErrorHandlerSax(PrintStream out) {
        this.out = out;
    }

    private String getParseExceptionInfo(SAXParseException spe) {
        String systemId = spe.getSystemId();

        if (systemId == null) {
            systemId = "null";
        }

        String info = "URI=" + systemId + " Line="
                + spe.getLineNumber() + ": " + spe.getMessage();

        return info;
    }

    public void warning(SAXParseException spe) throws SAXException {
        out.println("Warning: " + getParseExceptionInfo(spe));
    }

    public void error(SAXParseException spe) throws SAXException {
        String message = "Error: " + getParseExceptionInfo(spe);
        throw new SAXException(message);
    }

    public void fatalError(SAXParseException spe) throws SAXException {
        String message = "Fatal Error: " + getParseExceptionInfo(spe);
        throw new SAXException(message);
    }

}
