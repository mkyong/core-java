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
        setModifiedDate(path, Instant.now().minus(1, ChronoUnit.DAYS));

    }

    // set last modified time of all files from this path
    public static void setModifiedDate(Path path, Instant instant) throws IOException {

        try (Stream<Path> walk = Files.walk(path)) {
            walk
                    .filter(Files::isReadable)      // read permission
                    .filter(Files::isRegularFile)   // is a file
                    .forEach(p -> {
                        // set last modified time
                        try {
                            Files.setLastModifiedTime(p, FileTime.from(instant));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
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

}
