package com.mkyong.string.split;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitNewLines {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        sb.append("aaa \n");
        sb.append("bbb   \r\n");
        sb.append("ccc\n");
        sb.append("\n");
        sb.append("ddd\r\n");
        sb.append("\r\n");
        sb.append("eee\n");

        String text = sb.toString();
        System.out.println("---Original---");
        System.out.println(text);

        System.out.println("---Split---");

        // split by new line, trim and filter empty line
        List<String> lines = Arrays.stream(text.split("\\r?\\n"))
                .map(x -> x.trim())
                .filter(x -> x.length() > 0)
                .collect(Collectors.toList());

        for (String line : lines) {
            System.out.println(line);
        }

    }

}