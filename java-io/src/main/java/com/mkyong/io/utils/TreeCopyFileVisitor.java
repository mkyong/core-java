package com.mkyong.io.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

// https://docs.oracle.com/javase/tutorial/essential/io/examples/Copy.java
// https://stackoverflow.com/questions/6214703/copy-entire-directory-contents-to-another-directory
public class TreeCopyFileVisitor extends SimpleFileVisitor<Path> {

    private Path fromDir;
    private Path toDir;

    public TreeCopyFileVisitor(String toDir) {
        this.toDir = Paths.get(toDir);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        if (fromDir == null) {
            fromDir = dir;
        } else {

            // fromDir = /home/mkyong/test, dir = /home/mkyong/test/subtest , relativize = subtest
            System.out.println(fromDir.relativize(dir));

            // toDir = /home/mkyong/test2, relativize = subtest , resolve = /home/mkyong/test2/subtest
            System.out.println(toDir.resolve(fromDir.relativize(dir)));

            Path resolve = toDir.resolve(fromDir.relativize(dir));
            Files.createDirectories(resolve);
            System.out.println("Create directories : " + resolve);
        }
        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        Path resolve = toDir.resolve(fromDir.relativize(file));
        Files.copy(file, resolve);
        System.out.println("Copy File to : " + resolve);

        return FileVisitResult.CONTINUE;

    }
}
