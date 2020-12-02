package com.mkyong.io.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Files.find() examples
public class FileFindExample {

    private static DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\test\\");
        List<Path> result = findByFileName(path, "google.png");
        result.forEach(x -> System.out.println(x));

        /*Path path = Paths.get("C:\\Users\\mkyong\\Downloads");
        long fileSize = 1024 * 1024 * 100; // 100M
        List<Path> result = findByFileSize(path, fileSize);
        for (Path p : result) {
            System.out.println(String.format("%-40s [fileSize]: %,d", p, Files.size(p)));
        }*/

        // find files, LastModifiedTime from yesterday and above
        /*List<Path> result = findByLastModifiedTime(
                Paths.get("C:\\test"),
                Instant.now().minus(1, ChronoUnit.DAYS));

        for (Path p : result) {

            // formatting...
            BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
            FileTime time = attrs.lastModifiedTime();
            LocalDateTime localDateTime = time.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            System.out.println(String.format("%-40s [%s]", p, localDateTime.format(DATE_FORMATTER)));
        }*/

    }

    public static List<Path> findByFileName(Path path, String fileName)
            throws IOException {

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) ->
                        p.getFileName().toString().equalsIgnoreCase(fileName))
        ) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;

    }

    public static List<Path> findByFileName2(Path path, String fileName)
            throws IOException {

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) -> {
                    // if directory or no-read permission, ignore
                    if (Files.isDirectory(p) || !Files.isReadable(p)) {
                        return false;
                    }
                    return p.getFileName().toString().equalsIgnoreCase(fileName);
                })
        ) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;

    }

    public static List<Path> findByFileSize(Path path, long fileSize)
            throws IOException {

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) -> {
                    try {
                        if (Files.isDirectory(p)) {   // ignore directory
                            return false;
                        }
                        return Files.size(p) >= fileSize;
                    } catch (IOException e) {
                        System.err.println("Unable to get the file size of path : " + path);
                    }
                    return false;
                }

        )) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;

    }

    public static List<Path> findByLastModifiedTime(Path path, Instant instant)
            throws IOException {

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) -> {

                    if (Files.isDirectory(p) || !Files.isReadable(p)) {
                        return false;
                    }

                    FileTime fileTime = basicFileAttributes.lastModifiedTime();
                    // negative if less, positive if greater
                    // 1 means fileTime equals or after the provided instant argument
                    // -1 means fileTime before the provided instant argument
                    int i = fileTime.toInstant().compareTo(instant);
                    return i > 0;
                }

        )) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;

    }

}
