package com.mkyong.string;

import java.nio.charset.StandardCharsets;

public class ConvertBytesToString2 {

    public static void main(String[] args) {

        String str = "This is raw text!";

        // string to byte[]
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        System.out.println("Text : " + str);
        System.out.println("Text [Byte Format] : " + bytes);

        // no, don't do this, it returns the address of the object in memory
        System.out.println("Text [Byte Format] toString() : " + bytes.toString());

        // convert byte[] to string
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Output : " + s);

        // old code, UnsupportedEncodingException
        // String s1 = new String(bytes, "UTF_8");

    }

}