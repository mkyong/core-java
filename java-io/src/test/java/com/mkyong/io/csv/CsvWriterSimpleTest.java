package com.mkyong.io.csv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvWriterSimpleTest {

    private CsvWriterSimple writer = new CsvWriterSimple();

    @Test
    void test_convert_csv_line_default() {
        String[] record = {"1", "apple", "10", "9.99"};
        String expected = "\"1\",\"apple\",\"10\",\"9.99\"";

        String result = writer.convertToCsvFormat(record);
        assertEquals(expected, result);
    }

    @Test
    void test_convert_csv_line_empty() {
        String[] record = {"1", "", "10", ""};
        String expected = "\"1\",\"\",\"10\",\"\"";

        String result = writer.convertToCsvFormat(record);
        assertEquals(expected, result);
    }

    @Test
    void test_convert_csv_line_custom_separator() {
        String[] record = {"1", "apple", "10", "9.99"};
        String expected = "\"1\";\"apple\";\"10\";\"9.99\"";

        String result = writer.convertToCsvFormat(record, ";");
        assertEquals(expected, result);
    }

    @Test
    void test_convert_csv_line_no_quoted() {
        String[] record = {"1", "apple", "10", "9.99"};
        String expected = "1,apple,10,9.99";

        String result = writer.convertToCsvFormat(record, ",", false);
        assertEquals(expected, result);
    }

    @Test
    void test_convert_csv_line_contains_comma() {
        String[] record = {"1", "apple,orange", "10", "9.99"};
        String expected = "\"1\",\"apple,orange\",\"10\",\"9.99\"";

        String result = writer.convertToCsvFormat(record);
        assertEquals(expected, result);
    }

    @Test
    void test_convert_csv_line_contains_double_quotes() {
        String[] record = {"1", "12\"apple", "10", "9.99"};
        String expected = "\"1\",\"12\"\"apple\",\"10\",\"9.99\"";

        String result = writer.convertToCsvFormat(record);
        assertEquals(expected, result);
    }

    @Test
    void test_convert_csv_line_contains_newline() {
        String[] record = {"1", "promotion!\napple", "10", "9.99"};
        String expected = "\"1\",\"promotion!\napple\",\"10\",\"9.99\"";

        String result = writer.convertToCsvFormat(record);
        assertEquals(expected, result);
    }

}
