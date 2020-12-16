package com.mkyong.java8.stream.flatmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlatMapExample1 {

    public static void main(String[] args) {

        //if(arrsy1.)
        /*String[] reduce = stream1.reduce(
                new String[]{},
                (a, b) -> Stream.concat(Stream.of(a), Stream.of(b))
                        .toArray(String[]::new)
        );*/

        /*Stream<Stream<String>> streamStream = stream1.map(x -> Arrays.stream(x));
        Stream<String> stringStream = stream1.flatMap(Arrays::stream);
        result.forEach(System.out::println);*/

        /**
         * [a, b]
         * [c, d]
         * [e, f]
         */
        // print a stream of array
        //stream1.forEach(x -> System.out.println(Arrays.toString(x)));

        /*Stream<Stream<String>> stream2 = stream1.map(Arrays::stream);
        Stream<Stream<String>> result = stream2.filter(x -> !"a".equals(x));
        List<String> result2 = result.map(x -> x.toString()).collect(Collectors.toList());
        result2.forEach(System.out::println);

        Stream<String> flattedStream = stream1.flatMap(x -> Arrays.stream(x));
        Stream<String> result = flattedStream.filter(x -> !"a".equals(x));
        result.forEach(System.out::println);*/

        //filter a stream of string[], and return a string[]?
        //Stream<String[]> stream = temp.forEach(System.out::println););

        //stream.forEach(System.out::println);

    }

    /*private static void test() {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        List<String> collect = Stream.of(array)
                .flatMap(Stream::of)
                .filter(x -> !"a".equals(x))
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }*/

    private static void example3() {
        Developer o1 = new Developer();
        o1.setName("mkyong");
        o1.addBook("Java 8 in Action");
        o1.addBook("Spring Boot in Action");
        o1.addBook("Effective Java (3nd Edition)");

        Developer o2 = new Developer();
        o2.setName("zilap");
        o2.addBook("Learning Python, 5th Edition");
        o2.addBook("Effective Java (3nd Edition)");

        List<Developer> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);

        // hmm....Set...Set...
        //Set<Set<String>> collect = list.stream()
        //        .map(x -> x.getBook())
        //        .collect(Collectors.toSet());

        Set<String> collect =
                list.stream()
                        .map(x -> x.getBook())                              //  Stream<Set<String>>
                        .flatMap(x -> x.stream())                           //  Stream<String>
                        .filter(x -> !x.toLowerCase().contains("python"))   //  filter python book
                        .collect(Collectors.toSet());

        /*Set<String> collect2 =
                list.stream()
                        .flatMap(x -> x.getBook().stream())                 //  Stream<String>
                        .filter(x -> !x.toLowerCase().contains("python"))   //  filter python book
                        .collect(Collectors.toSet());*/

        collect.forEach(System.out::println);
    }
}
