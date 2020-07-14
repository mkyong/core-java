package com.mkyong.io.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Java 1.1, 1.2
public class CreateFile2 {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/newFile.txt";

        try {

            File file = new File(fileName);

            // true if file does no exist and was created successfully.
            // false if file is already exists
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

            // Write to file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Hello World!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}