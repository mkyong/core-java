package com.mkyong.java13.jep355;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiLineString6 {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Stream<String> lines = Files.lines(
                Paths.get(ClassLoader.getSystemResource("test.html").toURI())
        );

        System.out.println(lines.collect(Collectors.joining("\n")));

    }
}
