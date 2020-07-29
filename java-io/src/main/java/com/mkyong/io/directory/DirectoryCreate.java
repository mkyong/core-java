package com.mkyong.io.directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryCreate {

    public static void main(String[] args) {

        String dir = "/home/mkyong/test2/test3/test4/";
        createDirectoriesNIO(dir);

    }

    public static void createDirectoriesNIO(String dir) {

        try {

            Path path = Paths.get(dir);
            Files.createDirectories(path);
            System.out.println("Directory is created!");

            //Files.createDirectory(path);

        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

    }

    public static void createDirectoriesLegacy(String dir) {

        File file = new File(dir);

        if (file.mkdirs()) {
            System.out.println("Directory is created!");
        } else {
            System.out.println("Failed to create directory!");
        }

    }

}
