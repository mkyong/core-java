package com.mkyong.io.temp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

public class CreateTempFile {

    public static void main(String[] args) {

        //String tmpdir = System.getProperty("java.io.tmpdir");
        //System.out.println("Temp file path: " + tmpdir);

        try {

            // Create a temporary file
            //Path temp = Files.createTempFile(null, ".log");

            // Create a temporary file in a specified folder
            Path path = Paths.get("/home/mkyong/test/");
            //Path temp = Files.createTempFile(path, null, ".log");

            Path tempFile = Files.createTempFile(path, null, ".log");
            Files.setPosixFilePermissions(tempFile, PosixFilePermissions.fromString("rwxrwxrwx"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
