package com.mkyong.io.howto;

import java.io.File;
import java.nio.file.Path;

public class FileToPath {

    public static void main(String[] args) {

        File file = new File("/home/mkyong/test/file.txt");

        // Java 1.7, convert File to Path
        Path path = file.toPath();

        System.out.println(path);

        /*Path path = Paths.get("/home/mkyong/test/file.txt");

        File file = path.toFile();

        System.out.println(file);*/

    }
}
