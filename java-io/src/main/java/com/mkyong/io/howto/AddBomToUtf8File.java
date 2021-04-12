package com.mkyong.io.howto;

import org.apache.commons.codec.binary.Hex;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// https://en.wikipedia.org/wiki/Byte_order_mark
public class AddBomToUtf8File {

    //byte[] BOM = {(byte) 0xEF,(byte) 0xBB,(byte) 0xBF};

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/home/mkyong/file.txt");
        writeBomFile(path, "mkyong");
        removeBom(path);

    }

    private static void writeBomFile(Path path, String content) {
        // Java 8 default UTF-8
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write("\ufeff");
            bw.write(content);
            bw.newLine();
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private static void writeBomFile2(Path path, String content) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toFile())
                        , StandardCharsets.UTF_8))) {
            bw.write("\ufeff");
            bw.write(content);
            bw.newLine();
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeBomFile3(Path path, String content) {
        try (PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toFile()), StandardCharsets.UTF_8))) {
            //pw.write("\ufeff");
            pw.write(0xfeff); // alternative, codepoint
            pw.write(content);
            pw.write(System.lineSeparator());
            pw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeBomFile4(Path path, String content) {
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {

            byte[] BOM = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

            fos.write(BOM);
            fos.write(content.getBytes(StandardCharsets.UTF_8));
            fos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            fos.write(content.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private static boolean isContainBOM(Path path) throws IOException {

        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Path: " + path + " does not exists!");
        }

        boolean result = false;

        byte[] bom = new byte[3];
        try (InputStream is = new FileInputStream(path.toFile())) {

            // read 3 bytes of a file.
            is.read(bom);

            // BOM encoded as ef bb bf
            String content = new String(Hex.encodeHex(bom));
            if ("efbbbf".equalsIgnoreCase(content)) {
                result = true;
            }

        }

        return result;
    }

    private static void removeBom(Path path) throws IOException {

        if (isContainBOM(path)) {

            byte[] bytes = Files.readAllBytes(path);

            ByteBuffer bb = ByteBuffer.wrap(bytes);

            System.out.println("Found BOM!");

            byte[] bom = new byte[3];
            // get the first 3 bytes
            bb.get(bom, 0, bom.length);

            // remaining
            byte[] contentAfterFirst3Bytes = new byte[bytes.length - 3];
            bb.get(contentAfterFirst3Bytes, 0, contentAfterFirst3Bytes.length);

            System.out.println("Remove the first 3 bytes, and overwrite the file!");

            // override the same path
            Files.write(path, contentAfterFirst3Bytes);

        } else {
            System.out.println("This file doesn't contains UTF-8 BOM!");
        }

    }
}
