package com.mkyong.io.howto.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectoryExample {

    public static void main(String[] args) {

        // zip the entire java 8 jdk folder
        Path source = Paths.get("/usr/lib/jvm/java-8-openjdk-amd64/");
        //Path source = Paths.get("/home/mkyong/test/");

        if (!Files.isDirectory(source)) {
            System.out.println("Please provide a folder.");
            return;
        }

        try {

            //ZipDirectoryExample.zipFolder(source);
            ZipDirectoryExample.zipFolderNio(source);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

    public static void zipFolderNio(Path source) throws IOException {

        // get current working directory
        String currentPath = System.getProperty("user.dir") + File.separator;

        // get folder name as zip file name, can be other extension, .foo .bar .whatever
        String zipFileName = source.getFileName().toString() + ".zip";
        URI uri = URI.create("jar:file:" + currentPath + zipFileName);

        Files.walkFileTree(source, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

                // Copying of symbolic links not supported
                if (attributes.isSymbolicLink()) {
                    return FileVisitResult.CONTINUE;
                }

                Map<String, String> env = new HashMap<>();
                env.put("create", "true");

                try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {

                    Path targetFile = source.relativize(file);
                    Path pathInZipfile = zipfs.getPath(targetFile.toString());

                    // NoSuchFileException, need create parent directories in zip path
                    if (pathInZipfile.getParent() != null) {
                        Files.createDirectories(pathInZipfile.getParent());
                    }

                    // copy file attributes
                    CopyOption[] options = {
                            StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.COPY_ATTRIBUTES,
                            LinkOption.NOFOLLOW_LINKS
                    };
                    // Copy a file into the zip file path
                    Files.copy(file, pathInZipfile, options);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.printf("Unable to zip : %s%n%s%n", file, exc);
                return FileVisitResult.CONTINUE;
            }

        });

    }

    // zip a directory, including sub files and sub directories
    public static void zipFolder(Path source) throws IOException {

        // get folder name as zip file name
        String zipFileName = source.getFileName().toString() + ".zip";

        try (
                ZipOutputStream zos = new ZipOutputStream(
                        new FileOutputStream(zipFileName))
        ) {

            Files.walkFileTree(source, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

                    // only copy files, no symbolic links
                    if (attributes.isSymbolicLink()) {
                        return FileVisitResult.CONTINUE;
                    }

                    try (FileInputStream fis = new FileInputStream(file.toFile())) {

                        Path targetFile = source.relativize(file);
                        zos.putNextEntry(new ZipEntry(targetFile.toString()));

                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }

                        // if large file, throws out of memory
                        //byte[] bytes = Files.readAllBytes(file);
                        //zos.write(bytes, 0, bytes.length);

                        zos.closeEntry();

                        System.out.printf("Zip file : %s%n", file);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.printf("Unable to zip : %s%n%s%n", file, exc);
                    return FileVisitResult.CONTINUE;
                }
            });

        }

    }

}