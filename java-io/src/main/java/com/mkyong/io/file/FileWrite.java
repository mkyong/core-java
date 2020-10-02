package com.mkyong.io.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWrite {

    private static final String NEW_LINE = System.lineSeparator();

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/home/mkyong/test/aaa.txt");
        writeFile(path, "Hello World 1" + NEW_LINE);

        String content = "...";
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));

    }

    // Java 7
    private static void writeFile(Path path, String content) throws IOException {

        // file does not exist, create and write it
        // if the file exists, override the content
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));

        // Append mode
        // if the file exists, append string to the end of file.
        // Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        // if file does not exist, throws NoSuchFileException
        // if the file exists, append it
        // Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    // Java 11
    private static void writeFileJava11(Path path, String content) throws IOException {

        // default utf_8
        // file does not exist, create and write it
        // if the file exists, override the content
        // Files.writeString(path, content);

        // Append mode
        Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private static void writeFile(Path path, List<String> list) throws IOException {

        // Iterable
        // Files.write(path, list, StandardCharsets.UTF_8);

        // Java 8, default utf_8
        Files.write(path, list);

    }

    // Java 8
    private static void writeFileJava8(Path path, String content) throws IOException {

        // default utf_8
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write(content);
            bw.newLine();
        }

        // append mode
        /*try (BufferedWriter bw = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(content);
            bw.newLine();
        }*/

    }

    private static void writeFile_FileWriter(File file, String content) throws IOException {

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
            bw.newLine(); // add new line, System.lineSeparator()
        }

        // append mode
        /*try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
            bw.newLine();
        }*/

    }

    // FileOutputStream is meant for writing streams of raw bytes such as image data.
    // For writing streams of characters, consider using FileWriter.
    private static void writeFile_FileOutputStream(File file, byte[] bytes) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        }

        // append mode
        /*try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }*/

    }

}