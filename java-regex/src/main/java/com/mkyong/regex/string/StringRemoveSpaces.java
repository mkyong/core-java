package com.mkyong.regex.string;

public class StringRemoveSpaces {

    public static void main(String[] args) {

        String text = "Hello       World       Java.";

        String result = text.replaceAll("\\s+", " ");

        System.out.println(result);

    }

}
