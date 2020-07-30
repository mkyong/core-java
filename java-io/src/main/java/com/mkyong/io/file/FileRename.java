package com.mkyong.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileRename {

    public static void main(String[] args) {

        String file1 = "/home/mkyong/hello.txt";
        String file2 = "/home/mkyong/newfolder/hello2.txt";

        Path source = Paths.get(file1);
        Path target = Paths.get(file2);

        try {

            // rename a file to other path
            //Files.move(source, target);

            // rename a file in the same directory
            Files.move(source, source.resolveSibling("newName.txt"));

            // move file to another directory, using the same file name
            //Path newDir = Paths.get("/home/mkyong/newfolder/");

            // create the directories, if directory exits, no effect
            //Files.createDirectories(newDir);

            //Files.move(source, newDir.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);

            // apache commons-io
            //FileUtils.moveFile(new File("/source"), new File("/target"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
