package com.mkyong.io.howto;

import com.mkyong.io.utils.TreeCopyFileVisitor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyDirectory {

    public static void main(String[] args) {

        String fromDirectory = "/home/mkyong/test/";
        String toToDirectory = "/home/mkyong/test2/";

        try {
            copyDirectoryNIO(fromDirectory, toToDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

    public static void copyDirectoryNIO(String fromDir, String toDir) throws IOException {

        //Path fromFile = Paths.get(from);
        //Path toFile = Paths.get(to);

        // didn't copy files inside the folder
        //Files.copy(fromFile, toFile);

        Files.walkFileTree(Paths.get(fromDir), new TreeCopyFileVisitor(toDir));


    }

    public static void copyFileCommonIO(String from, String to) throws IOException {

        File fromDir = new File(from);
        File toDir = new File(to);

        FileUtils.copyDirectory(fromDir, toDir);

    }


}
