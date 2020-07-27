package com.mkyong.io.howto;

import com.mkyong.io.utils.TreeCopyFileVisitor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class CopyDirectory {

    public static void main(String[] args) {

        String fromDirectory = "/home/mkyong/test/";
        String toToDirectory = "/home/mkyong/test2/";

        try {
            copyDirectoryJavaNIO(Paths.get(fromDirectory), Paths.get(toToDirectory));
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

    public static void copyDirectoryJavaNIO(Path source, Path target)
            throws IOException {

        // is this a directory?
        if (Files.isDirectory(source)) {

            //if target directory exist?
            if (Files.notExists(target)) {
                // create it
                Files.createDirectories(target);
                System.out.println("Directory created : " + target);
            }

            // list all files or folders from the source, Java 1.8, returns stream
            // try-with-resources, auto-close
            try (Stream<Path> paths = Files.list(source)) {

                paths.forEach(p -> {

                    try {
                        // create target path from the source path
                        Path targetPath = target.resolve(source.relativize(p));
                        copyDirectoryJavaNIO(p, targetPath);
                    } catch (IOException e) {
                        System.err.println("IO errors : " + e.getMessage());
                    }

                });

            }

        } else {
            // if file exists, replace it
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File created : " + target);
        }
    }

}
