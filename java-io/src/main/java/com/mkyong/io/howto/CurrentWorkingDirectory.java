package com.mkyong.io.howto;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class CurrentWorkingDirectory {

    public static void main(String[] args) {

        printCurrentWorkingDirectory1();
        printCurrentWorkingDirectory2();
        printCurrentWorkingDirectory3();
        printCurrentWorkingDirectory4();

    }

    // System Property
    private static void printCurrentWorkingDirectory1() {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
    }

    // Path
    private static void printCurrentWorkingDirectory2() {
        String userDirectory = Paths.get("")
                .toAbsolutePath()
                .toString();
        System.out.println(userDirectory);
    }

    // File("")
    private static void printCurrentWorkingDirectory3() {
        String userDirectory = new File("").getAbsolutePath();
        System.out.println(userDirectory);
    }

    // FileSystems
    private static void printCurrentWorkingDirectory4() {
        String userDirectory = FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString();
        System.out.println(userDirectory);
    }

}
