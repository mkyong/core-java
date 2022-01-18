package com.mkyong.string;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ConvertBytesToStringBase64 {

    public static void main(String[] args) {

        String filepath = "/Users/mkyong/phone.png";
        Path path = Paths.get(filepath);

        if (Files.notExists(path)) {
            throw new IllegalArgumentException("File is not exists!");
        }

        try {

            // convert the file's content to byte[]
            byte[] bytes = Files.readAllBytes(path);

            // encode, byte[] to Base64 encoded string
            String s = Base64.getEncoder().encodeToString(bytes);
            System.out.println(s);

            // decode, Base64 encoded string to byte[]
            byte[] decode = Base64.getDecoder().decode(s);

            // save into another image file.
            Files.write(Paths.get("/Users/mkyong/phone2.png"), decode);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}