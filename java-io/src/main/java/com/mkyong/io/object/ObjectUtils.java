package com.mkyong.io.object;

import java.io.*;
import java.math.BigDecimal;

public class ObjectUtils {

    // Serialization
    // Save object into a file.
    public static void writeObject(Person obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    // Deserialization
    // Get object from a file.
    public static Person readObject(File file) throws IOException, ClassNotFoundException {
        Person result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (Person) ois.readObject();
        }
        return result;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person = new Person("mkyong", 40, new BigDecimal(900));

        // object -> file
        ObjectUtils.writeObject(person, new File("person.obj"));

        // file -> object
        Person obj = ObjectUtils.readObject(new File("person.obj"));
        System.out.println(obj);

    }
}
