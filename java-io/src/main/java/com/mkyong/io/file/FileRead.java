package com.mkyong.io.file;

import com.mkyong.io.utils.ResourceHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

// https://www.mkyong.com/java/java-how-to-read-a-file/
public class FileRead {

    public static void main(String[] args) throws IOException {

        //String fileName = "/home/mkyong/large.txt";
        String fileName = ResourceHelper.getAbsoluteFilePath("app.log");
        readFileJava8(fileName);

    }

    public static void readFileJava8(String fileName) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(fileName));

        // parallel
        //lines.parallel().forEach(l -> {});

        // does not preserve order
        lines.forEach(System.out::println);

        // preserve order
        //lines.forEachOrdered(l -> {});

        // returns a List<String>, for large file, it throws java.lang.OutOfMemoryError: Java heap space
        // List<String> collect = lines.collect(Collectors.toList());
    }

    public static void readFileJava7(String fileName) throws IOException {

        // Java 7
        // max file size 2G
        // java.lang.OutOfMemoryError: Required array size too large
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(content);

        // Java 8
        // java.lang.OutOfMemoryError: Java heap space
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        lines.forEach(System.out::println);

    }

    // max file size 2G
    // java.lang.OutOfMemoryError: Required array size too large
    public static void readFileJava11(String fileName) throws IOException {
        String s = Files.readString(Paths.get(fileName));
        System.out.println(s);
    }

    // good in everything
    public static void readFileBufferedReader(String fileName) throws IOException {

        // default buffer size 8k
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        int bufferSize = 10240; // 10k
        try (BufferedReader br = new BufferedReader(new FileReader(fileName), bufferSize)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Java 8, better? no idea
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

    }

    // slow in reading large file, BufferedReader is better
    public static void readFileScanner(String fileName) throws IOException {
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
            }
        }
    }

    public static void readFileBufferedReaderClassic(String fileName) {

        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }

    }



    /*public static void displayMemoryInfo() {
        Runtime rt = Runtime.getRuntime();
        System.out.println("\ntotal memory: " + rt.totalMemory() / 1024 / 1024 + " MB");
        System.out.println("free memory: " + rt.freeMemory() / 1024 / 1024 + " MB");
        System.out.println("consumed memory: " + (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024 + " MB");
    }*/
}
