package com.mkyong.io.howto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InputStreamToReader {

    public static void main(String[] args) throws IOException {

        String line;
        // loads a file from a resources folder
        InputStream is = InputStreamToReader.class.getClassLoader().getResourceAsStream("csv/country.csv");

        // BufferedReader -> InputStreamReader -> InputStream
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

    }

}