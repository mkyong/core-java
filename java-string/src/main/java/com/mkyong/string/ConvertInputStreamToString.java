package com.mkyong.string;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertInputStreamToString {

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {

        URI uri = URI.create("https://www.google.com/");
        try (InputStream inputStream = uri.toURL().openStream()) {

            // Convert InputStream -> String
            String result = convertInputStreamToString4(inputStream);

            System.out.println(result);

        }

    }

    // Plain Java
    private static String convertInputStreamToString(InputStream is) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        // Java 1.1
        //return result.toString(StandardCharsets.UTF_8.name());

        return result.toString("UTF-8");

        // Java 10
        //return result.toString(StandardCharsets.UTF_8);

    }

    // @Java 9 -> inputStream.readAllBytes()
    // max bytes Integer.MAX_VALUE, 2147483647, which is 2G
    private static String convertInputStreamToStringJava9(InputStream inputStream)
            throws IOException {

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    // InputStreamReader + StringBuilder
    private static String convertInputStreamToString2(InputStream inputStream)
            throws IOException {

        final char[] buffer = new char[8192];
        final StringBuilder result = new StringBuilder();

        // InputStream -> Reader
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            int charsRead;
            while ((charsRead = reader.read(buffer, 0, buffer.length)) > 0) {
                result.append(buffer, 0, charsRead);
            }
        }

        return result.toString();

    }

    // InputStreamReader + BufferedReader (modify line breaks)
    private static String convertInputStreamToString3(InputStream inputStream)
            throws IOException {

        String newLine = System.getProperty("line.separator");
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result
                        .append(line)
                        .append(newLine);
            }
        }
        return result.toString();

    }

    // Java 8 BufferedReader#lines (modify line breaks)
    private static String convertInputStreamToString4(InputStream inputStream)
            throws IOException {

        String newLine = System.getProperty("line.separator");
        String result;
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
            result = lines.collect(Collectors.joining(newLine));
        }

        return result;

    }

}