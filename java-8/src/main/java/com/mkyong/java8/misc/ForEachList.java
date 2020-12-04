package com.mkyong.java8.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ForEachList {

    public static void main(String[] args) {
        loopListJava8();
        //loopListClassic();
    }

    public static void loopListJava8() {

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add(null);
        list.add("D");
        list.add("E");

        // lambda
        //list.forEach(x -> System.out.println(x));

        // method reference
        list.forEach(System.out::println);

        // filter null value
        list.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);

    }

    public static void loopListClassic() {

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        // normal
        for (String l : list) {
            System.out.println(l);
        }

    }

}
