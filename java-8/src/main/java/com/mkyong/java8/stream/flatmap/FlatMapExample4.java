package com.mkyong.java8.stream.flatmap;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlatMapExample4 {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6};

        //Stream<int[]>
        Stream<int[]> streamArray = Stream.of(array);

        //Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(x -> System.out.println(x));

        /*long[] longArray = {1, 2, 3, 4, 5, 6};
        Stream<long[]> longArray1 = Stream.of(longArray);
        LongStream longStream = longArray1.flatMapToLong(x -> Arrays.stream(x));
        System.out.println(longStream.count());*/

    }
}
