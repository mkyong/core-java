package com.mkyong.io.directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

public class DirectoryDelete {

    public static void createDummyFiles() throws IOException {
        Files.createDirectories(Paths.get("/home/mkyong/test2/test3/test4/test5/"));
        Files.write(Paths.get("/home/mkyong/test2/test2.log"), "hello".getBytes());
        Files.write(Paths.get("/home/mkyong/test2/test3/test3.log"), "hello".getBytes());
    }

    public static void main(String[] args) {

        String dir = "/home/mkyong/test2/";

        try {

            createDummyFiles();
            deleteDirectoryJava7(dir);
            //deleteDirectoryJava8(dir);
            //FileUtils.deleteDirectory(new File(dir));
            //deleteDirectoryLegacyIO(new File(dir));

            System.out.println("Done");

        } catch (IOException e) {
            System.err.printf("Failed to delete the directory %n%s%n", e);
        }

        /*try {

            // DirectoryNotEmptyException, who will delete a empty folder? Java....
            Files.delete(Paths.get(dir));

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public static void deleteDirectoryJava7(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        Files.walkFileTree(path,
                new SimpleFileVisitor<>() {

                    // delete directories or folders
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir,
                                                              IOException exc) throws IOException {
                        Files.delete(dir);
                        System.out.printf("Directory is deleted : %s%n", dir);
                        return FileVisitResult.CONTINUE;
                    }

                    // delete file
                    @Override
                    public FileVisitResult visitFile(Path file,
                                                     BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        System.out.printf("File is deleted : %s%n", file);
                        return FileVisitResult.CONTINUE;
                    }
                }
        );

    }

    public static void deleteDirectoryJava8(String dir) throws IOException {

        Path path = Paths.get(dir);

        // read java doc, Files.walk need close the resources
        // try-with-resources to ensure that the stream's open directories are closed
        try (Stream<Path> walk = Files.walk(path)) {
            walk
                    .sorted(Comparator.reverseOrder())
                    .forEach(DirectoryDelete::deleteDirectoryJava8Extract);
        }

    }

    // extract method to handle exception in lambda
    public static void deleteDirectoryJava8Extract(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.printf("Unable to delete this path : %s%n%s", path, e);
        }
    }

    public static void deleteDirectoryLegacyIO(File file) {

        File[] list = file.listFiles();
        if (list != null) {
            for (File temp : list) {
                //recursive delete
                System.out.println("Visit " + temp);
                deleteDirectoryLegacyIO(temp);
            }
        }

        if (file.delete()) {
            System.out.printf("Delete : %s%n", file);
        } else {
            System.err.printf("Unable to delete file or directory : %s%n", file);
        }

    }

}
