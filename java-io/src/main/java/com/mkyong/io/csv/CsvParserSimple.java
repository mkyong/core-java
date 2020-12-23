package com.mkyong.io.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * https://tools.ietf.org/html/rfc4180
 * <p>
 * Fields containing line breaks (CRLF), double quotes, and commas
 * should be enclosed in double-quotes.  For example:
 * <p>
 * "aaa","b CRLF
 * bb","ccc" CRLF
 * zzz,yyy,xxx
 * <p>
 * If double-quotes are used to enclose fields, then a double-quote
 * appearing inside a field must be escaped by preceding it with
 * another double quote.  For example:
 * <p>
 * "aaa","b""bb","ccc"
 */
public class CsvParserSimple {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DOUBLE_QUOTES = '"';
    private static final char DEFAULT_QUOTE_CHAR = DOUBLE_QUOTES;

    private boolean isMultiLine = false;
    private String pendingField = "";
    private String[] pendingFieldLine = new String[]{};

    private boolean isFieldStarted = false;
    private boolean isFieldClosed = false;

    public static void main(String[] args) throws Exception {

        URL resource = CsvParserSimple.class.getClassLoader().getResource("csv/wikipedia.csv");
        File file = Paths.get(resource.toURI()).toFile();

        CsvParserSimple obj = new CsvParserSimple();
        List<String[]> result = obj.readFile(file);
        result.forEach(x -> System.out.println(Arrays.toString(x)));

    }

    public List<String[]> readFile(File csvfile) throws Exception {

        List<String[]> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvfile))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] csvLineInArray = parseLine(line);

                // multiline
                if (pendingField.length() > 0) {
                    pendingFieldLine = Arrays.copyOf(csvLineInArray, csvLineInArray.length);
                } else {

                    // join two arrays
                    if (pendingFieldLine != null && pendingFieldLine.length > 0) {
                        String[] r = Stream.concat(Arrays.stream(pendingFieldLine), Arrays.stream(csvLineInArray))
                                .toArray(String[]::new);

                        result.add(r);
                        pendingFieldLine = null;
                    } else {
                        result.add(csvLineInArray);
                    }

                }


            }
        }

        return result;
    }

    public String[] parseLine(String line) throws Exception {
        return parseLine(line, DEFAULT_SEPARATOR);
    }

    public String[] parseLine(String line, char separator) throws Exception {
        return parse(line, separator, DEFAULT_QUOTE_CHAR).toArray(String[]::new);
    }

    private List<String> parse(String line, char separator, char quoteChar) throws Exception {

        List<String> result = new ArrayList<>();

        boolean inQuotes = false;
        boolean isFieldWithEmbeddedDoubleQuotes = false;

        StringBuilder field = new StringBuilder();

        // convert line to char[] and loop one by one
        for (char c : line.toCharArray()) {

            // handle embedded double quotes ""
            if (c == DOUBLE_QUOTES) {
                if (isFieldWithEmbeddedDoubleQuotes) {
                    field.append(DOUBLE_QUOTES);
                    isFieldWithEmbeddedDoubleQuotes = false;
                } else {
                    isFieldWithEmbeddedDoubleQuotes = true;
                }
            } else {
                isFieldWithEmbeddedDoubleQuotes = false;
            }

            // for multiline
            // any pending from the previous field?
            if (pendingField.length() > 0) {
                field.append(pendingField);
                pendingField = "";
                inQuotes = true;
            }

            // General parsing
            if (c == quoteChar) {
                inQuotes = !inQuotes;
            } else {
                // if find separator and not in quotes, add field to a list's entry
                if (c == separator && !inQuotes) {
                    result.add(field.toString());
                    field.setLength(0);
                } else {
                    // else append the char into a field
                    field.append(c);
                }
            }

        }

        //line done, what to do?
        if (inQuotes) {
            // multiline
            pendingField = field.toString();
        } else {
            //if remaining, add to result
            if (field.length() > 0) {
                result.add(field.toString());
            }
        }

        return result;

    }

}
