package com.mkyong.io.howto;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class GetDirectorySize {

    public static void main(String[] args) {

        String dir = "/home/mkyong/projects/core-java/";

        long size = getDirectorySizeJava7(Paths.get(dir));
        System.out.printf("Total size %,d bytes%n", size);

        long size2 = getDirectorySizeCommonIO(new File(dir));
        System.out.printf("Total size %,d bytes%n", size2);

        long size3 = getDirectorySizeJava8(Paths.get(dir));
        System.out.printf("Total size %,d bytes%n", size3);

        long size4 = getDirectorySizeLegacy(new File(dir));
        System.out.printf("Total size %,d bytes%n", size4);

    }

    public static long getDirectorySizeJava8(Path path) {

        long size = 0;

        // need close Files.walk
        try (Stream<Path> walk = Files.walk(path)) {

            size = walk
                    //.peek(System.out::println) // debug
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        // ugly, can pretty it with an extract method
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.out.printf("Failed to get size of %s%n%s", p, e);
                            return 0L;
                        }
                    })
                    .sum();

        } catch (IOException e) {
            System.out.printf("IO errors %s", e);
        }

        return size;

    }

    public static long getDirectorySizeCommonIO(File dir) {

        return FileUtils.sizeOfDirectory(dir);

    }

    public static long getDirectorySizeJava7(Path path) {

        AtomicLong size = new AtomicLong(0);

        try {

            Files.walkFileTree(path, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    // sum size
                    size.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    System.out.printf("Failed to get size of %s%n%s", file, e);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            System.out.printf("%s", e);
        }

        return size.get();

    }

    public static long getDirectorySizeLegacy(File dir) {

        long length = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile())
                    length += file.length();
                else
                    length += getDirectorySizeLegacy(file);
            }
        }
        return length;

    }

}