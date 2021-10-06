package com.mkyong.io.object;

import java.io.*;
import java.math.BigDecimal;

public class HelloDeserializationFilter {

    public static void main(String[] args) {

        //Person person = new Person("mkyong", 40, new BigDecimal(900));

        // reject this Person2, only allow Person class
        Person2 person = new Person2("mkyong", 40, new BigDecimal(900), "test");

        byte[] bytes = convertObjectToBytes(person);

        // only allow to deserialize com.mkyong.io.object.Person and java.base/*
        // !* reject all
        ObjectInputFilter filter =
                ObjectInputFilter.Config.createFilter(
                        "com.mkyong.io.object.Person;java.base/*;!*");

        Person p = (Person) convertBytesToObject(bytes, filter);

        System.out.println(p);
    }

    // Convert object to byte[]
    public static byte[] convertObjectToBytes(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }

    // Convert byte[] to object
    public static Object convertBytesToObject(byte[] bytes, ObjectInputFilter filter) {
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {

            // add filter before readObject
            ois.setObjectInputFilter(filter);

            return ois.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }

}
