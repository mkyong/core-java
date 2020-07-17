package com.mkyong.io.temp;

public class CreateTempFile {

    public static void main(String[] args) {

        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);

    }

}
