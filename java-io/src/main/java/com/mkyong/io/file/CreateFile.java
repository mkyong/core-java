package com.mkyong.io.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CreateFile {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/newFile.txt";

        // Java 10, new constructor, support Charsets
        try (PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8)) {

            writer.println("first line!");
            writer.println("second line!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Java 1.5
        /*try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {

            writer.println("first line!");
            writer.println("second line!");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
