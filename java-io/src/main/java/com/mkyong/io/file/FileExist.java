package com.mkyong.io.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileExist {

    public static void main(String[] args) {

        Path path = Paths.get("/home/mkyong/test/test.log");

        // check exists for file and directory
        if (Files.exists(path)) {

            if (Files.isRegularFile(path)) {
                System.out.println("File exists!");
            }
            if (Files.isDirectory(path)) {
                System.out.println("File exists, but it is a directory.");
            }

        } else {
            System.out.println("File doesn't exist");
        }

        /*if(Files.exists(path) && !Files.isDirectory(path)) {
            System.out.println("File exists!");
        }*/

        // If file is a symbolic links, it follows by default.
        //if(Files.exists(path, LinkOption.NOFOLLOW_LINKS)){
        //}

        /*if(Files.notExists(path)){
            System.out.println("File doesn't exist");
        }*/
    }

    // legacy io, no support for symbolic links
    private static void fileExists(String fileName){

        File file = new File(fileName);
        if(file.exists() && !file.isDirectory()){
            System.out.println("File exists!");
        }else{
            System.out.println("File doesn't exist");
        }

    }
}
