package com.mkyong.io.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private static final char DEFAULT_QUOTE_CHARACTER = '"';

    public String[] parseLine(String line) throws IOException {
        return parseLine(line, DEFAULT_SEPARATOR);
    }

    public String[] parseLine(String line, char separator) throws IOException {
        return parseLine(line, separator, DEFAULT_QUOTE_CHARACTER);
    }

    public String[] parseLine(String line, char separator, char quoteChar) throws IOException {
        return parse(line, separator, quoteChar).toArray(String[]::new);
    }

    private List<String> parse(String line, char separator, char quoteChar) {

        List<String> result = new ArrayList<>();

        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        StringBuilder field = new StringBuilder();

        // convert line to char[] and loop one by one
        for (char c : line.toCharArray()) {

            if (c == quoteChar) {
                inQuotes = !inQuotes;
            } else {
                // if find separator and not in quotes, add chars to a field.
                if (c == separator && !inQuotes) {
                    result.add(field.toString());
                    field.setLength(0);
                } else {
                    // else add the char into a field
                    field.append(c);
                }
            }

        }

        //line done, what to do? if remaining, add to result
        if (field.length() > 0) {
            result.add(field.toString());
        }

        return result;

    }


    /*public static List<List<String>> read(File file) throws Exception {

        List<List<String>> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                List<String> strings = parseLine(line, ',', '"');
                result.add(strings);
            }
        }

        return result;
    }*/

}
