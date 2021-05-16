package com.mkyong.xml.tips;

import java.io.*;
import java.util.Properties;

public class ConvertXmlToProperties {

    public static void main(String[] args) throws IOException {

        Properties props = new Properties();
        try (InputStream input =
                     new FileInputStream("src/main/resources/server.xml")) {
            // loads from XML into a properties file
            props.loadFromXML(input);
        }

        try (OutputStream output =
                     new FileOutputStream("c:\\test\\server.properties")) {

            props.store(output, "");

        }

    }

}
