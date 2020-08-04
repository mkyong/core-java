package com.mkyong.io.directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryRenameMove {

    public static void main(String[] args) {

        String file1 = "/home/mkyong/hello.txt";
        String file2 = "/home/mkyong/newfolder/hello2.txt";

        Path source = Paths.get(file1);
        Path target = Paths.get(file2);

        try {

            // rename a file in the same directory
            Files.move(source, source.resolveSibling("newName.txt"));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
