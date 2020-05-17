package com.mkyong.java11.jep323;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalVar {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "c");
        String result = list.stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.joining(","));
        System.out.println(result);

        String result2 = list.stream()
                .map((var x) -> x.toUpperCase())
                .collect(Collectors.joining(","));
        System.out.println(result2);

        List<String> list2 = Arrays.asList("a", "b", "c", null);
        String result3 = list2.stream()
                .map((@NotNull var x) -> x.toUpperCase())
                .collect(Collectors.joining(","));
        System.out.println(result3);


    }
}
