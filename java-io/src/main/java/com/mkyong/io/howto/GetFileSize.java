package com.mkyong.io.howto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetFileSize {

    public static void main(String[] args) {

        // this image is around 140kb
        String fileName = "/home/mkyong/Pictures/temp.png";
        printFileSizeNIO(fileName);
        //printFileSize(fileName);
    }

    public static void printFileSizeNIO(String fileName) {

        Path path = Paths.get(fileName);

        try {

            // size of a file (in bytes)
            long bytes = Files.size(path);
            System.out.println(String.format("%,d bytes", bytes));
            System.out.println(String.format("%,d kilobytes", bytes / 1024));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printFileSize(String fileName) {

        File file = new File(fileName);

        if (file.exists()) {

            // size of a file (in bytes)
            long bytes = file.length();

            long kilobytes = (bytes / 1024);
            long megabytes = (kilobytes / 1024);
            long gigabytes = (megabytes / 1024);
            long terabytes = (gigabytes / 1024);
            long petabytes = (terabytes / 1024);
            long exabytes = (petabytes / 1024);
            long zettabytes = (exabytes / 1024);
            long yottabytes = (zettabytes / 1024);

            System.out.println(String.format("%,d bytes", bytes));
            System.out.println(String.format("%,d kilobytes", kilobytes));
            System.out.println(String.format("%,d megabytes", megabytes));
            System.out.println(String.format("%,d gigabytes", gigabytes));
            System.out.println(String.format("%,d terabytes", terabytes));
            System.out.println(String.format("%,d petabytes", petabytes));
            System.out.println(String.format("%,d exabytes", exabytes));
            System.out.println(String.format("%,d zettabytes", zettabytes));
            System.out.println(String.format("%,d yottabytes", yottabytes));

        } else {
            System.out.println("File does not exist!");
        }

    }

}
