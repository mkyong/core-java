package com.mkyong.io.howto.compress;

import net.lingala.zip4j.ZipFile;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileExample {

    public static void main(String[] args) {

        Path source = Paths.get("/home/mkyong/test/Test.java");
        String zipFileName = "example.zip";

        try {

            //ZipFileExample.zipSingleFile(source, zipFileName);
            ZipFileExample.zipFileWithoutSaveLocal(zipFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

    // Zip a single file
    public static void zipSingleFile(Path source, String zipFileName) throws IOException {

        if (!Files.isRegularFile(source)) {
            System.err.println("Please provide a file.");
            return;
        }

        try (
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));
                FileInputStream fis = new FileInputStream(source.toFile());
        ) {

            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }

    }

    // Zip a single file
    public static void zipSingleFileNio(Path source, String zipFileName) throws IOException {

        if (!Files.isRegularFile(source)) {
            System.err.println("Please provide a file.");
            return;
        }

        Map<String, String> env = new HashMap<>();
        // Create the zip file if it doesn't exist
        env.put("create", "true");

        URI uri = URI.create("jar:file:/home/mkyong/" + zipFileName);

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Path pathInZipfile = zipfs.getPath(source.getFileName().toString());

            // Copy a file into the zip file path
            Files.copy(source, pathInZipfile, StandardCopyOption.REPLACE_EXISTING);
        }

    }

    // create a file (without save locally) and add to zip
    public static void zipFileWithoutSaveLocal(String zipFileName) throws IOException {

        String data = "Test data \n123\n456";
        String fileNameInZip = "abc.txt";

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName))) {

            ZipEntry zipEntry = new ZipEntry(fileNameInZip);
            zos.putNextEntry(zipEntry);

            ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
            // one line, able to handle large size?
            //zos.write(bais.readAllBytes());

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bais.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            zos.closeEntry();
        }

    }

    public static void zip4j() throws IOException {

        // zip file with a single file
        new ZipFile("filename.zip").addFile("file.txt");

        // zip file with multiple files
        List<File> files = Arrays.asList(
                new File("file1.txt"), new File("file2.txt"));
        new ZipFile("filename.zip").addFiles(files);

        // zip file with a folder
        new ZipFile("filename.zip").addFolder(new File("/home/mkyong/folder"));

    }
}