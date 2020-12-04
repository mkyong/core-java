package com.mkyong.java8.misc;

import java.util.stream.Stream;

public class forEachOrder {

    public static void main(String[] args) {

        Stream<String> s = Stream.of("a", "b", "c", "1", "2", "3");

        // Does not guarantee to the encounter order of the stream
        // s.parallel().forEach(x -> System.out.println(x));

        // keep order, it is always a,b,c,1,2,3
        s.parallel().forEachOrdered(x -> System.out.println(x));

    }
}
