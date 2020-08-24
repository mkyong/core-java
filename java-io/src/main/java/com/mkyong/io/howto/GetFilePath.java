package com.mkyong.io.howto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetFilePath {

    public static void main(String[] args) {

        // full file path
        File file1 = new File("/home/mkyong/test/file.txt");
        System.out.println("[File] : " + file1);
        printFilePath(file1);

        // a file name
        File file2 = new File("file.txt");
        System.out.println("\n[File] : " + file2);
        printFilePath(file2);

        // a soft or symbolic link
        File file3 = new File("/home/mkyong/test/soft-link");
        System.out.println("\n[File] : " + file3);
        printFilePath(file3);

        // a file contain `..`
        File file4 = new File("/home/mkyong/test/../hello.txt");
        System.out.println("\n[File] : " + file4);
        printFilePath(file4);

        // full path
        Path path1 = Paths.get("/home/mkyong/test/file.txt");
        System.out.println("\n[Path] : " + path1);
        printPath(path1);

        // file name
        Path path2 = Paths.get("file.txt");
        System.out.println("\n[Path] : " + path2);
        printPath(path2);

        // soft or symbolic link
        Path path3 = Paths.get("/home/mkyong/test/soft-link");
        System.out.println("\n[Path] : " + path3);
        printPath(path3);

        // a path contains `..`
        Path path4 = Paths.get("/home/mkyong/test/../hello.txt");
        System.out.println("\n[Path] : " + path4);
        printPath(path4);

    }

    static void printPath(Path path) {

        System.out.printf("%-25s : %s%n", "path", path);
        System.out.printf("%-25s : %s%n", "path.toAbsolutePath()", path.toAbsolutePath());
        System.out.printf("%-25s : %s%n", "path.getParent()", path.getParent());
        System.out.printf("%-25s : %s%n", "path.getRoot()", path.getRoot());

        try {

            if (Files.notExists(path)) {
                return;
            }

            // default, follow symbolic link
            System.out.printf("%-25s : %s%n", "path.toRealPath()", path.toRealPath());
            // no follow symbolic link
            System.out.printf("%-25s : %s%n", "path.toRealPath(nofollow)", path.toRealPath(LinkOption.NOFOLLOW_LINKS));

            // alternative to check isSymbolicLink
            /*if (Files.isSymbolicLink(path)) {
                Path link = Files.readSymbolicLink(path);
                System.out.println(link);
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // If a single file name, not full path, the file refer to
    // System.getProperty("user.dir") + file
    static void printFilePath(File file) {
        // print File = print file.getPath()
        System.out.printf("%-25s : %s%n", "file.getPath()", file.getPath());
        System.out.printf("%-25s : %s%n", "file.getAbsolutePath()", file.getAbsolutePath());
        try {
            System.out.printf("%-25s : %s%n", "file.getCanonicalPath()", file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("%-25s : %s%n", "Parent Path", getParentPath(file));
    }

    // if unable to get parent, try substring to get the parent folder.
    private static String getParentPath(File file) {
        if (file.getParent() == null) {
            String absolutePath = file.getAbsolutePath();
            return absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
        } else {
            return file.getParent();
        }
    }

}
