package com.mkyong.io.howto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileTraverseExample {

    public static void main(String[] args) {

        // Specify the directory we want to traverse
        String dirPath = "/Users/yongmookkim/projects/test/";

        // Traverse the directory and process files
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {

            paths
                    .filter(Files::isRegularFile)  // ensure it is a file
                    .forEach(System.out::println); // prints the file path

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
