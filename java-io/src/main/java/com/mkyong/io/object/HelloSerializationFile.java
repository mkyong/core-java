package com.mkyong.io.object;

import java.io.*;
import java.math.BigDecimal;

public class HelloSerializationFile {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person = new Person("mkyong", 50, new BigDecimal(1000));

        File file = new File("person.bin");

        writeObjectToFile(person, file);

        Person p = readObjectFromFile(file);

        //Person p = (Person) readObjectFromFile2(file);

        System.out.println(p);

    }

    // Serialization
    // Save object into a file.
    public static void writeObjectToFile(Person obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    // Deserialization
    // Get object from a file.
    public static Person readObjectFromFile(File file) throws IOException, ClassNotFoundException {
        Person result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (Person) ois.readObject();
        }
        return result;
    }

    // Deserialization
    // Get object from a file.
    public static Object readObjectFromFile2(File file) throws IOException, ClassNotFoundException {
        Object result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = ois.readObject();
        }
        return result;
    }

}
