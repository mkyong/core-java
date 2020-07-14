package com.mkyong.io.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateFileNio {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/newFile.txt";
        String content = "hello world!";

        try {

            // Java 1.7
            // default, create and write to it.
            Files.write(
                    Paths.get(fileName),
                    content.getBytes(StandardCharsets.UTF_8));

            // Append content
            /*Files.write(
                    Paths.get(fileName),
                    content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
