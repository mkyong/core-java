package com.mkyong.io.object;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializationUtils {

    // Serialization
    // Save object into a file.
    public static void writeObjectToFile(Person obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    // Serialization
    // Convert object to OutputStream
    public static void writeObjectToStream(Object obj, OutputStream output) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(output)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    // Serialization
    // Convert object to byte[]
    public static byte[] writeObjectToStream(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
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

    // Deserialization
    // generic example
    @SuppressWarnings("unchecked")
    public static <T> T readObject(InputStream is, Class<T> anyClass) throws IOException, ClassNotFoundException {
        T result = null;
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            result = (T) ois.readObject();
        }
        return result;
    }

    public static byte[] serialize(Object o) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(ba).writeObject(o);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return ba.toByteArray();
    }

    public static Object deserialize(byte[] bytes) {
        try {
            return new ObjectInputStream(
                    new ByteArrayInputStream(bytes)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Object deserializeFilter(byte[] bytes, ObjectInputFilter filter) {

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {

            ois.setObjectInputFilter(filter);

            return ois.readObject();
        }catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person = new Person("mkyong", 41, new BigDecimal(900));

        // object -> file

        // example 1
        // SerializationUtils.writeObjectToFile(person, new File("person.obj"));

        // example 2
        SerializationUtils.writeObjectToStream(person, new FileOutputStream("person.obj"));

        // example 3
        // byte[] bytes = SerializationUtils.writeObjectToStream(person);
        // Files.write(Paths.get("person.obj"), bytes);

        // file -> object
        //Person obj = SerializationUtils.readObject(new File("person.obj"));

        Person obj = SerializationUtils.readObject(
                new FileInputStream("person.obj"), Person.class);

        System.out.println(obj);

    }
}
