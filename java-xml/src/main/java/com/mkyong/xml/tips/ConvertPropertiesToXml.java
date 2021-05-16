package com.mkyong.xml.tips;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConvertPropertiesToXml {

    public static void main(String[] args) throws IOException {

        /*Properties props = new Properties();
        props.setProperty("email.support", "donot-spam-me@nospam.com");
        props.setProperty("http.port", "8080");
        props.setProperty("http.server", "localhost");*/

        Properties props = new Properties();
        try (InputStream input =
                     new FileInputStream("src/main/resources/application.properties")) {
            // loads a properties file
            props.load(input);
        }

        try (OutputStream output =
                     new FileOutputStream("c:\\test\\server-config.xml")) {

            // convert the properties to an XML file
            props.storeToXML(output, "Server config file", StandardCharsets.UTF_8);

        }

    }

}
