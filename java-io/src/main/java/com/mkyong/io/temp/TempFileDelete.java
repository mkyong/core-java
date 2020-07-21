package com.mkyong.io.temp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TempFileDelete {

    public static void main(String[] args) {

        try {

            Path temp = Files.createTempFile(null, ".log");
            System.out.println(temp);

            // if the file doesn't exist throws NoSuchFileException
            // Files.delete(temp);

            // write a line
            Files.write(temp, "Hello World".getBytes(StandardCharsets.UTF_8));

            // check if file exists before delete
            boolean result = Files.deleteIfExists(temp);
            if (result) {
                System.out.println("File is success delete.");
            } else {
                System.out.println("File doesn't exist.");
            }

        } catch (IOException e) {
            System.err.println("Unable to delete the file!");
            e.printStackTrace();
        }

    }

    /*private void deleteTempFileIO() {

        try {

            File tempFile = File.createTempFile("abc_", ".log");
            System.out.println(tempFile);

            boolean result = tempFile.delete();
            if (result) {
                System.out.println(tempFile.getName() + " is deleted!");
            } else {
                System.out.println("Sorry, unable to delete the file.");
            }

            // delete when JVM exit normally.
            // tempFile.deleteOnExit();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}
