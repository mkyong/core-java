package com.mkyong.io.howto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileTraverseExample2 {

    public static void main(String[] args) {

        List<String> result;

        // Specify the directory we want to traverse
        String dirPath = "/Users/yongmookkim/projects/test/";

        // Traverse the directory and process files
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {

            result = paths
                    .filter(Files::isRegularFile)  // ensure it is a file
                    .map(p -> p.getFileName().toString())
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total files: " + result.size());
        for (String name : result) {
            System.out.println("FileName: " + name);
        }


    }
}
