package com.mkyong.io.file;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

// https://mkyong.com/java/how-to-append-content-to-file-in-java/
public class FileAppend {

    private static final String NEW_LINE = System.lineSeparator();

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/home/mkyong/test/abc.txt");
        appendToFile(path, "hello world" + NEW_LINE);

        /*File file = new File("/home/mkyong/test/abc.txt");
        appendToFileFileWriter(file, "hello world" + NEW_LINE);
        System.out.println("Done");*/

    }

    // Java 7
    private static void appendToFile(Path path, String content) throws IOException {

        // if file not exists throws java.nio.file.NoSuchFileException
        //  Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        // if file not exists, create it otherwise append to the end of the file
        Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

    }


    // append lines of text
    private static void appendToFileJava8(Path path, List<String> list) throws IOException {

        // Java 7?
        // Files.write(path, list, StandardCharsets.UTF_8,
        //        StandardOpenOption.CREATE,
        //        StandardOpenOption.APPEND);

        // Java 8, default utf_8
        Files.write(path, list,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

    }

    // Java 11, writeString, append mode
    private static void appendToFileJava11(Path path, String content) throws IOException {

        // utf_8
        // Files.writeString(path, content, StandardCharsets.UTF_8,
        //        StandardOpenOption.CREATE,
        //        StandardOpenOption.APPEND);

        // default StandardCharsets.UTF_8
        Files.writeString(path, content,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    private static void appendToFileFileWriter(File file, String content) throws IOException {

        // default - create and write
        // if file not exists, create and write
        // if file exists, truncate and write
        /*try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
            bw.newLine();
        }*/

        // append
        // if file not exists, create and write
        // if file is exists, append to the end of the file
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
            bw.newLine();   // add new line, System.lineSeparator()
        }

    }

    private static void appendToFileFileWriter(File file, List<String> content) throws IOException {

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (String s : content) {
                bw.write(s);
                bw.newLine();
            }
        }

    }

    private static void appendToFileFileOutputStream(File file, String content) throws IOException {

        // append mode
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }

    }

    // append mode
    private static void appendToFileFileUtils(File file, String content) throws IOException {

        FileUtils.writeStringToFile(
                file, content, StandardCharsets.UTF_8, true);

    }


}
