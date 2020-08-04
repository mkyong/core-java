package com.mkyong.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDelete {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/app.log";
        deleteIfExistsJava7Nio(fileName);
        
    }

    public static void deleteIfExistsJava7Nio(String fileName) {
        try {
            boolean result = Files.deleteIfExists(Paths.get(fileName));
            if (result) {
                System.out.println("File is deleted!");
            } else {
                System.out.println("Sorry, unable to delete the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJava7Nio(String fileName) {
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJavaClassic(String fileName) {
        try {
            File file = new File(fileName);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Sorry, unable to delete the file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delete file on JVM exit
        // File file = new File(fileName);
        // file.deleteOnExit();


    }


}
