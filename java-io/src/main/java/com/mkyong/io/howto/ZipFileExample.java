package com.mkyong.io.howto;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileExample {

    public static void main(String[] args) {

        Path source = Paths.get("/home/mkyong/test/Test.java");
        String zipFileName = "example.zip";

        try {

            ZipFileExample.zipSingleFileNio(source, zipFileName);

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


}
