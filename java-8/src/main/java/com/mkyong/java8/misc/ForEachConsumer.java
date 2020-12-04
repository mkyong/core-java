package com.mkyong.java8.misc;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ForEachConsumer {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("abc", "java", "python");
        Stream<String> stream = Stream.of("abc", "java", "python");

        // convert String to Hex
        Consumer<String> printTextInHexConsumer = (String x) -> {
            StringBuilder sb = new StringBuilder();
            for (char c : x.toCharArray()) {
                String hex = Integer.toHexString(c);
                sb.append(hex);
            }
            System.out.print(String.format("%n%-10s:%s", x, sb.toString()));
        };

        list.forEach(printTextInHexConsumer);

        stream.forEach(printTextInHexConsumer);

    }

}
