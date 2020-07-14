package com.mkyong.io.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileJava8 {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/newFile.txt";

        Path path = Paths.get(fileName);

        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {

            writer.write("Hello World !!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Append mode.
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.APPEND)){

            writer.write("Hello World !!");

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}