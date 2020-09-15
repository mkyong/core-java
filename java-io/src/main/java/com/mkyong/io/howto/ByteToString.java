package com.mkyong.io.howto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ByteToString {

    public static void main(String[] args) {

        String example = "This is raw text!";
        // string to byte[]
        byte[] bytes = example.getBytes();

        System.out.println("Text : " + example);
        System.out.println("Text [Byte Format] : " + bytes);

        // no, don't do this, it returns the address of the object in memory
        System.out.println("Text [Byte Format] toString() : " + bytes.toString());

        // convert bytes[] to string
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Output : " + s);

        // UnsupportedEncodingException
        // String s1 = new String(bytes, "UTF_8");

    }

    private static void binaryByteArray() {

        String filepath = "/home/mkyong/phone.png";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filepath));

            // readable string in encoded in base64, easy transfer as a string
            // byte[] to base64 string
            String s = Base64.getEncoder().encodeToString(bytes);
            System.out.println(s);

            // base64 string to byte[]
            byte[] decode = Base64.getDecoder().decode(s);
            Files.write(Paths.get("/home/mkyong/phone2.png"), decode);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}