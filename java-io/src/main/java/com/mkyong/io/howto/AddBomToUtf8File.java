package com.mkyong.io.howto;

import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// https://en.wikipedia.org/wiki/Byte_order_mark
public class AddBomToUtf8File {

    // byte[] BOM = {(byte) 0xEF,(byte) 0xBB,(byte) 0xBF};

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/home/mkyong/file.txt");

        writeBomFile3(path, "mkyong");
        //removeBom(path);

    }

    private static void writeBomFile(Path path, String content) {
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write("\ufeff");
            bw.newLine();
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeBomFile2(Path path, String content) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path.toFile())
                        , StandardCharsets.UTF_8))) {
            bw.write("\ufeff");
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
            pw.write(0xfeff);
            pw.write(System.lineSeparator());
            pw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isValidUtf8BOM(byte[] bytes) {
        boolean result = false;

        // ensures 3 bytes
        if (bytes != null && bytes.length == 3) {
            // BOM encoded as ef bb bf
            String content = new String(Hex.encodeHex(bytes));
            if ("efbbbf".equalsIgnoreCase(content)) {
                result = true;
            }

        }
        return result;
    }

    private static void removeBom(Path path) throws IOException {

        byte[] bytes = Files.readAllBytes(path);

        // ByteBuffer is better way
        //byte[] first3Bytes = new byte[]{bytes[0], bytes[1], bytes[2]};

        ByteBuffer bb = ByteBuffer.wrap(bytes);

        byte[] bom = new byte[3];
        bb.get(bom, 0, bom.length);

        if (isValidUtf8BOM(bom)) {

            System.out.println("Found BOM!");

            // remove the first 3 bytes
            byte[] contentAfterFirst3Bytes = new byte[bytes.length - 3];
            bb.get(contentAfterFirst3Bytes, 0, contentAfterFirst3Bytes.length);

            System.out.println("Remove the first 3 bytes BOM, and overwrite the file!");

            // override the same path
            Files.write(path, contentAfterFirst3Bytes);

        } else {
            System.out.println("This file doesn't contains UTF-8 BOM!");
        }

    }
}
