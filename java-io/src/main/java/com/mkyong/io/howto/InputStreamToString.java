package com.mkyong.io.howto;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputStreamToString {

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {

        URI uri = URI.create("https://www.google.com/");
        try (InputStream inputStream = uri.toURL().openStream()) {

            // InputStream -> String
            String result = convertInputStreamToString(inputStream);
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
        return result.toString(StandardCharsets.UTF_8.name());

        // Java 10
        // return result.toString(StandardCharsets.UTF_8);

    }

    // Since Java 9
    private static String convertInputStreamToStringJava9(InputStream inputStream) throws IOException {
        // inputStream.readAllBytes() -> Java 9
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    private static String convertInputStreamToStringInputStreamReader(InputStream inputStream) throws IOException {

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

    // This method will convert the original line breaks to `line.separator` Windows to "\r\n", Linux "\n"
    private static String convertInputStreamToStringBufferedReader(InputStream inputStream) throws IOException {

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

    // This method will convert the original line breaks to `line.separator` Windows to "\r\n", Linux "\n"
    private static String convertInputStreamToStringJava8(InputStream inputStream) throws IOException {

        String newLine = System.getProperty("line.separator");
        String result;
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
            result = lines.collect(Collectors.joining(newLine));
        }

        return result;

    }

    // commons-io
    private static String convertInputStreamToStringCommonIO(InputStream inputStream) throws IOException {

        //StringWriter writer = new StringWriter();
        //IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8);
        //return writer.toString();

        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);

    }

}
