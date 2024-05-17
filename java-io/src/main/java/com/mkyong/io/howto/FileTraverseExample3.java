package com.mkyong.io.howto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class FileTraverseExample3 {

    public static void main(String[] args) {

        String dir = "/Users/yongmookkim/projects/test/";
        String findThisFile = "a.txt";

        try {

            Optional<Path> foundFile = findFileByName(Paths.get(dir), findThisFile);
            foundFile.ifPresentOrElse(
                    file -> System.out.println("File found: " + file),
                    () -> System.out.println("File not found.")
            );

        } catch (IOException e) {
            System.err.println("An error occurred while searching for the file: " + e.getMessage());
        }

    }

    public static Optional<Path> findFileByName(Path directory, String fileName) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst();
        }
    }

}
