package com.mkyong.io.howto;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// tested with character data and binary data
public class ByteToFile {

    public static void main(String[] args) {

        try {

            String filePath = "/home/mkyong/test/phone.png";

            // file to bytes[], jdk 7
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));

            //File file = new File(filePath);
            //byte[] bytes = Files.readAllBytes(file.toPath());

            // old way
            //byte[] bytes = readFileToBytes(filePath);

            // commons-io
            //byte[] bytes = FileUtils.readFileToByteArray(new File(filePath));

            // save byte[] to a file
            writeBytesToFile("/home/mkyong/test/phone2.png", bytes);
            writeBytesToFileNio("/home/mkyong/test/phone3.png", bytes);
            writeBytesToFileApache("/home/mkyong/test/phone4.png", bytes);

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //JDK 7 - FileOutputStream + try-with-resources
    private static void writeBytesToFile(String fileOutput, byte[] bytes) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(fileOutput)) {
            fos.write(bytes);
        }

    }

    //JDK 7, NIO, Files.write
    private static void writeBytesToFileNio(String fileOutput, byte[] bytes) throws IOException {

        Path path = Paths.get(fileOutput);
        Files.write(path, bytes);

    }

    // Apache IO
    private static void writeBytesToFileApache(String fileOutput, byte[] bytes) throws IOException {

        FileUtils.writeByteArrayToFile(new File(fileOutput), bytes);

    }


    // file to byte[], old and classic way, before Java 7
    private static void readFileToBytes(String filePath) throws IOException {

        File file = new File(filePath);
        byte[] bytes = new byte[(int) file.length()];

        FileInputStream fis = null;
        try {

            fis = new FileInputStream(file);

            //read file into bytes[]
            fis.read(bytes);

        } finally {
            if (fis != null) {
                fis.close();
            }
        }

    }

}
