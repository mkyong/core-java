package com.mkyong.io.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileCreate {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/test/file.txt";

        try {

            createFile_FileOutputStream(fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

    public static void createFile_FileOutputStream(String fileName) throws IOException {

        String data = "Test data (123)";

        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(data.getBytes());
        }

    }
}
