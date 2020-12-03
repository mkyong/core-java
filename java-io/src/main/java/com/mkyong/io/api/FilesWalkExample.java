package com.mkyong.io.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Files.walk example
public class FilesWalkExample {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\test\\");
        //List<Path> paths = listFiles(path);
        //List<Path> paths = listDirectories(path);
        //List<Path> paths = findByFileExtension(path, ".txt");
        //List<Path> paths = findByFileName(path, "readme.txt");

        //long fileSizeInBytes = 1024 * 1024 * 10; // 10MB
        //List<Path> paths = findByFileSize(path, fileSizeInBytes);
        //paths.forEach(x -> System.out.println(x));

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        setAllFilesModifiedDate(path, yesterday);

    }

    // list all files from this path
    public static List<Path> listFiles(Path path) throws IOException {

        List<Path> result;
        //try (Stream<Path> walk = Files.walk(path, 1)) {
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return result;

    }

    // list all directories from this path
    public static List<Path> listDirectories(Path path) throws IOException {

        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isDirectory)
                    .collect(Collectors.toList());
        }
        return result;

    }

    public static List<Path> findByFileExtension(Path path, String fileExtension)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isRegularFile)   // is a file
                    .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
        return result;

    }

    public static List<Path> findByFileName(Path path, String fileName) throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<Path> result;
        // walk file tree, no more recursive loop
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isReadable)      // read permission
                    .filter(Files::isRegularFile)   // is a file
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
                    .filter(Files::isReadable)              // read permission
                    .filter(p -> !Files.isDirectory(p))     // is a file
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

    // set all files' last modified time to this instant
    public static void setAllFilesModifiedDate(Path path, Instant instant) throws IOException {

        try (Stream<Path> walk = Files.walk(path)) {
            walk
                    .filter(Files::isReadable)      // read permission
                    .filter(Files::isRegularFile)   // file only
                    .forEach(p -> {
                        try {
                            // set last modified time
                            Files.setLastModifiedTime(p, FileTime.from(instant));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

}
