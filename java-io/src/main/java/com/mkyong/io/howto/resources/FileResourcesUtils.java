package com.mkyong.io.howto.resources;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileResourcesUtils {

    public static void main(String[] args) throws IOException {

        //String fileName = "database.properties";
        String fileName = "json/file1.json";

        FileResourcesUtils app = new FileResourcesUtils();

        System.out.println("getResourceAsStream : " + fileName);
        InputStream is = app.getFileFromResourceAsStream(fileName);
        printInputStream(is);

        System.out.println("\ngetResource : " + fileName);
        File file = app.getFileFromResource(fileName);
        printFile(file);

        // get all files from a resources folder (JAR version)
        /*try {

            List<Path> result = app.getPathsFromResourceJAR();
            for (Path path : result) {
                System.out.println("Path : " + path);
                InputStream is = app.getFileFromResourceAsStream(path.toString());
                printInputStream(is);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/

    }


    // get a file from resources folder
    // works in everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    // The a resource URL is not working in JAR file
    // Exception in thread "main" java.nio.file.NoSuchFileException:
    // file:/home/mkyong/projects/core-java/java-io/target/java-io.jar!/json/file1.json
    private File getFileFromResource(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.getFile());
        }

    }

    // Get all path from the JAR file
    private List<Path> getPathsFromResourceJAR() throws URISyntaxException, IOException {

        List<Path> result;

        // get path of the running JAR
        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        System.out.println("path :" + jarPath);

        URI uri = URI.create("jar:file:" + jarPath);

        try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {

            // get paths from src/main/resources/json
            result = Files.walk(fs.getPath("json"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        return result;

    }

    // Works in IDE and unit test, not working in JAR
    private List<File> getAllFilesFromResource() throws URISyntaxException, IOException {

        ClassLoader classLoader = getClass().getClassLoader();

        // src/main/resources/json
        URL resource = classLoader.getResource("json");

        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                .filter(Files::isRegularFile)
                .map(x -> x.toFile())
                .collect(Collectors.toList());

        return collect;
    }

    // print input stream
    private static void printInputStream(InputStream inputStream) throws IOException {

        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        }

    }

    // print a file
    private static void printFile(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
