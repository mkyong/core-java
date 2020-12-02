package com.mkyong.io.howto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindFile {

    public static void main(String[] args) throws IOException {

        //Path path = Paths.get("C:\\test");
        //List<Path> result = findFiles(path, "google.png");

        /*Path path = Paths.get("C:\\Users\\mkyong\\Downloads\\");
        long fileSizeInBytes = (1024 * 1024) * 100L; //100mb
        System.out.println(String.format("Search > FileSize : %,d", fileSizeInBytes));

        List<Path> result = findByFileSize(path, fileSizeInBytes);
        for (Path p : result) {
            String format = String.format("%-50s - %,d", p, Files.size(p));
            System.out.println(format);
        }*/

        Path path = Paths.get("C:\\$Recycle.Bin\\S-1-5-18");
        List<Path> result = findByFileName(path, "google.png");
        result.forEach(x -> System.out.println(x));

    }

    public static List<Path> findByFileName(Path path, String fileName) throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<Path> result;
        // walk file tree, no more recursive loop
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isReadable)
                    .filter(p -> !Files.isDirectory(p))
                    .filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName))
                    .collect(Collectors.toList());
        }
        return result;

    }

    // fileSize in bytes
    public static List<Path> findByFileSize(Path path, long fileSize) throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<Path> result;
        // walk file tree, no more recursive loop
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isReadable)    // avoid AccessDeniedException
                    .filter(p -> !Files.isDirectory(p))
                    .filter(p -> checkFileSize(p, fileSize))
                    .collect(Collectors.toList());
        }
        return result;

    }

    private static boolean checkFileSize(Path path, long fileSize) {
        boolean result = false;
        try {
            if (Files.size(path) >= fileSize) {
                result = true;
            }
        } catch (IOException e) {
            System.err.println("Unable to get the file size of this file: " + path);
        }
        return result;
    }

}
