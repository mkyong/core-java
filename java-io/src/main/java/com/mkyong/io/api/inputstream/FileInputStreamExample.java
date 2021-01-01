package com.mkyong.io.api.inputstream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileInputStreamExample {

    public static void main(String[] args) {
        String file = "c:\\test\\file.txt";
        String fileUnicode = "c:\\test\\file-unicode.txt";

        readFile(file);
        //readFileBetterPerformance(fileUnicode);
        //readFileBetterInputStreamReader(fileUnicode);

    }

    private static void readFile(String fileName) {

        try (FileInputStream fis = new FileInputStream(new File(fileName))) {

            // remaining bytes that can be read
            System.out.println("Remaining bytes that can be read : " + fis.available());

            int content;
            // reads a byte at a time, if end of the file, returns -1
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);

                System.out.println("Remaining bytes that can be read : " + fis.available());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readFileBetterPerformance(String fileName) {

        try (FileInputStream fis = new FileInputStream(new File(fileName))) {

            // remaining bytes that can be read
            System.out.println("Remaining bytes that can be read : " + fis.available());

            // 8k a time
            byte[] bytes = new byte[8192];

            // reads 8192 bytes at a time, if end of the file, returns -1
            while (fis.read(bytes) != -1) {

                // convert bytes to string for demo
                // also convert unicode to string
                System.out.println(new String(bytes, StandardCharsets.UTF_8));

                System.out.println("Remaining bytes that can be read : " + fis.available());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readFileBetterPerformance2(String fileName) {

        try (BufferedInputStream bis =
                     new BufferedInputStream(
                             new FileInputStream(new File(fileName)))) {

            // remaining bytes that can be read
            System.out.println("Remaining bytes that can be read : " + bis.available());

            int content;
            // reads 8192 bytes at a time and buffers them until they are needed,
            // if end of the file, returns -1
            while ((content = bis.read()) != -1) {

                // convert bytes to string for demo
                System.out.println((char) content);

                System.out.println("Remaining bytes that can be read : " + bis.available());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readFileBetterInputStreamReader(String fileName) {

        try (BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(new File(fileName))))) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
