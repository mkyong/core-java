package com.mkyong.java14.jep359;

import java.io.*;

record GPS(double latitude, double longitude) implements Serializable {
};

public class Java14FileApp {

    private static final String FILE_PATH = "location.obj";

    public static void main(String[] args) {

        GPS obj = new GPS(10, 20);
        save(obj, FILE_PATH);

        GPS result = read(FILE_PATH);
        System.out.println(result);

    }

    private static void save(GPS obj, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static GPS read(String path) {
        GPS result = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            result = (GPS) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
