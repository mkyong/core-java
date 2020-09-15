package com.mkyong.io.howto;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringToByte {

    public static void main(String[] args) {

        String example = "This is an example";

        // string to byte[]
        byte[] bytes = example.getBytes(StandardCharsets.UTF_8);

        // print
        System.out.println("Text : " + example);
        System.out.println("Text [Byte Format] : " + Arrays.toString(bytes));

    }
}
