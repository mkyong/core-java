package com.mkyong.io.howto.compress;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipExample {

    public static void main(String[] args) {

        // compress
        Path source = Paths.get("/home/mkyong/test/sitemap.xml");
        Path target = Paths.get("/home/mkyong/test/sitemap.xml.gz");

        //Path source = Paths.get("/home/mkyong/test/video.mp4");
        //Path target = Paths.get("/home/mkyong/test/video.mp4.gz");

        // decompress
        //Path source = Paths.get("/home/mkyong/test/sitemap.xml.gz");
        //Path target = Paths.get("/home/mkyong/test/sitemap3.xml");

        if (Files.notExists(source)) {
            System.err.printf("The path %s doesn't exist!", source);
            return;
        }

        try {

            GzipExample.compressGzip(source, target);
            //GZipExample.compressGzipNio(source, target);

            //GZipExample.decompressGzip(source, target);
            //GZipExample.decompressGzipNio(source, target);

            //byte[] bytes = GZipExample.decompressGzipToBytes(source);
            //convert bytes[] to string for display
            //System.out.println(new String(bytes, StandardCharsets.UTF_8));

            //Path target = Paths.get("/home/mkyong/test/data.gz");
            //GZipExample.compressStringToGzip("hello world", target);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // classic
    public static void compressGzip(Path source, Path target) throws IOException {

        try (GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(target.toFile()));
             FileInputStream fis = new FileInputStream(source.toFile())) {

            // copy file (FileInputStream) to GZIPOutputStream
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                gos.write(buffer, 0, len);
            }

        }

    }

    // nio Files.copy, it works
    public static void compressGzipNio(Path source, Path target) throws IOException {

        try (GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(target.toFile()))) {
            Files.copy(source, gos);
        }

    }

    // compress data to gzip directly
    public static void compressStringToGzip(String data, Path target) throws IOException {

        try (GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(target.toFile()))) {

            gos.write(data.getBytes(StandardCharsets.UTF_8));

        }

    }

    public static void decompressGzip(Path source, Path target) throws IOException {

        try (GZIPInputStream gis = new GZIPInputStream(new FileInputStream(source.toFile()));
             FileOutputStream fos = new FileOutputStream(target.toFile())) {

            // copy GZIPInputStream to FileOutputStream
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

        }

    }

    public static void decompressGzipNio(Path source, Path target) throws IOException {

        try (GZIPInputStream gis = new GZIPInputStream(new FileInputStream(source.toFile()))) {
            Files.copy(gis, target);
        }

    }

    // decompress a Gzip file into a byte arrays
    public static byte[] decompressGzipToBytes(Path source) throws IOException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (GZIPInputStream gis = new GZIPInputStream(new FileInputStream(source.toFile()))) {

            // copy GZIPInputStream to ByteArrayOutputStream
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                output.write(buffer, 0, len);
            }

        }

        return output.toByteArray();

    }

}
