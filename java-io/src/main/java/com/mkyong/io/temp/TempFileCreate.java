package com.mkyong.io.temp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TempFileCreate {

    public static void main(String[] args) {

        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);

        try {

            // Create a temporary file
            Path temp = Files.createTempFile(null, ".log");

            // Create a temporary file in a specified folder
            //Path path = Paths.get("/home/mkyong/test/");
            //Path temp = Files.createTempFile(path, null, ".log");

            // file permission
            //Path tempFile = Files.createTempFile(path, null, ".log");
            //Files.setPosixFilePermissions(tempFile, PosixFilePermissions.fromString("rwxrwxrwx"));

            System.out.println(temp);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*private void createTempFileIO() {

        try {

            File file = File.createTempFile("abc_", ".binary");
            System.out.println(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
