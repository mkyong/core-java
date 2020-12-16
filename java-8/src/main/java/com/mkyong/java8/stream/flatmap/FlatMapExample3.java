package com.mkyong.java8.stream.flatmap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FlatMapExample3 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\test\\test.txt");

        // read file to a stream of lines
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);

        // stream of array...hard to process.
        // Stream<String[]> words = lines.map(line -> line.split(" +"));

        // stream of stream of string....hmm...better flat to one level.
        // Stream<Stream<String>> words = lines.map(line -> Stream.of(line.split(" +")));

        // split by spaces...result a stream of words, good!
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));

        // count the number of words.
        long noOfWords = words.count();
        System.out.println(noOfWords);
    }
}
