package com.mkyong.string;

import java.nio.charset.StandardCharsets;

public class ConvertBytesToString {

    public static void main(String[] args) {

        // String to byte[]
        byte[] bytes = "mkyong".getBytes(StandardCharsets.UTF_8);

        // byte[] to String
        String s = new String(bytes, StandardCharsets.UTF_8);

        // mkyong
        System.out.println(s);

    }

}