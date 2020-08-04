package com.mkyong.io.file;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.*;

public class FileCopy {

    public static void main(String[] args) {

        String fromFile = "/home/mkyong/data/db.debug.conf";
        String toFile = "/home/mkyong/data/deploy/db.conf";

        try {
            copyFileNIO(fromFile, toFile);
            //copyFilePlainJava(fromFile, toFile);
            //copyFileGuava(fromFile, toFile);
            //copyFileCommonIO(fromFile, toFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Copy file is done.");
    }

    // if toFile exists, replace it.
    public static void copyFileCommonIO(String from, String to) throws IOException {

        File fromFile = new File(from);
        File toFile = new File(to);

        FileUtils.copyFile(fromFile, toFile);

    }

    // if toFile exists, replace it.
    public static void copyFileGuava(String from, String to) throws IOException {

        File fromFile = new File(from);
        File toFile = new File(to);

        // @Beta?
        com.google.common.io.Files.copy(fromFile, toFile);

    }

    // if toFile exists, replace it.
    public static void copyFilePlainJava(String from, String to) throws IOException {

        InputStream inStream = null;
        OutputStream outStream = null;

        try {

            File fromFile = new File(from);
            File toFile = new File(to);

            inStream = new FileInputStream(fromFile);
            outStream = new FileOutputStream(toFile);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
                outStream.flush();
            }

        } finally {
            if (inStream != null)
                inStream.close();

            if (outStream != null)
                outStream.close();
        }

    }

    public static void copyFileNIO(String from, String to) throws IOException {

        Path fromFile = Paths.get(from);
        Path toFile = Paths.get(to);

        // if fromFile doesn't exist, Files.copy throws NoSuchFileException
        if (Files.notExists(fromFile)) {
            System.out.println("File doesn't exist? " + fromFile);
            return;
        }

        // if toFile folder doesn't exist, Files.copy throws NoSuchFileException
        // if toFile parent folder doesn't exist, create it.
        Path parent = toFile.getParent();
        if (parent != null) {
            if (Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
        }

        // default - if toFile exist, throws FileAlreadyExistsException
        //Files.copy(fromFile, toFile);

        // if toFile exist, replace it.
        //Files.copy(fromFile, toFile, StandardCopyOption.REPLACE_EXISTING);

        // multiple StandardCopyOption
        /*CopyOption[] options = { StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES,
                LinkOption.NOFOLLOW_LINKS };

        Files.copy(fromFile, toFile, options);*/

    }

}
