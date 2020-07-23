package com.mkyong.io.howto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

public class GetCreationDate {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/test";

        try {

            Path file = Paths.get(fileName);

            //BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            //System.out.println("creationTime: " + attr.creationTime());

            FileTime creationTime = (FileTime) Files.getAttribute(file, "creationTime");
            System.out.println("creationTime: " + creationTime);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}