package com.mkyong.string;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ConvertStringToBytes {

    public static void main(String[] args) {

        String example = "This is an example";

        //  default charset, a bit dangerous
        byte[] output1 = example.getBytes();

        // in old days, before java 1.7
        byte[] output2 = example.getBytes(Charset.forName("UTF-8"));

        // the best , java 1.7+
        byte[] output3 = example.getBytes(StandardCharsets.UTF_8);

        System.out.println("Text : " + example);

        // base64 encode
        String base64encoded = Base64.getEncoder().encodeToString(output3);

        System.out.println("Text [Base64] : " + base64encoded);

    }

}
