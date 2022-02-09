package com.mkyong.string.split;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitJava8 {

    public static void main(String[] args) {

        String phone = "012-3456789";

        /*List<String> output = Pattern.compile("-")
                .splitAsStream(phone)
                .collect(Collectors.toList());*/

        List<String> output = Arrays.stream(phone.split("-"))
                .collect(Collectors.toList());

        System.out.println(output);


    }

}
