package com.mkyong.xml.jaxb;

import com.mkyong.xml.jaxb.model.Fruit;
import jakarta.xml.bind.*;

import java.io.File;

// http://blog.bdoughan.com/2011/05/specifying-eclipselink-moxy-as-your.html
public class JaxbExampleFruit {

    public static void main(String[] args) {

        JAXBContext jaxbContext = null;
        try {

            // Normal JAXB RI
            //jaxbContext = JAXBContext.newInstance(Fruit.class);

            // EclipseLink MOXy needs jaxb.properties at the same package with Fruit.class
            // Alternative, I prefer define this via eclipse JAXBContextFactory manually.
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Fruit.class}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // change XML encoding
            // jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

            // remove <?xml version="1.0" encoding="UTF-8"?>
            //jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            Fruit o = new Fruit();
            o.setId(1);
            o.setName("Banana");
            o.setPrice("9.99");

            // output to a xml file
            //jaxbMarshaller.marshal(o, new File("C:\\test\\fruit.xml"));

            // output to console
            jaxbMarshaller.marshal(o, System.out);

            // XML Unmarshalling
            /*File file = new File("C:\\test\\fruit.xml");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Fruit o = (Fruit) jaxbUnmarshaller.unmarshal(file);
            System.out.println(o);*/

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
