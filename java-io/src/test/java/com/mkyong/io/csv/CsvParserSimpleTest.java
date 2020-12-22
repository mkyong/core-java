package com.mkyong.io.csv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserSimpleTest {

    private CsvParserSimple parser = new CsvParserSimple();
    //private CSVParser parser = new CSVParser();

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {
            "\"aa\",\"bb\",\"cc\",\"dd\",\"ee\"",
            "aa,bb,cc,dd,ee",
            "aa,bb,\"cc\",dd,ee"
    }
    )
    void test_normal_csv_line_default(String line) throws Exception {
        String[] result = parser.parseLine(line);
        assertEquals(5, result.length);
        assertEquals("aa", result[0]);
        assertEquals("bb", result[1]);
        assertEquals("cc", result[2]);
        assertEquals("dd", result[3]);
        assertEquals("ee", result[4]);
    }

    // RFC 4180 require comma as separator, but in real life , it can be semi colon,
    // not everyone follow RFC.
    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {
            "\"aa\";\"bb\";\"cc\";\"dd\";\"ee\"",
            "aa;bb;cc;dd;ee",
            "aa;bb;\"cc\";dd;ee"
    }
    )
    void test_normal_csv_line_custom_separator(String line) throws Exception {
        String[] result = parser.parseLine(line, ';');
        assertEquals(5, result.length);
        assertEquals("aa", result[0]);
        assertEquals("bb", result[1]);
        assertEquals("cc", result[2]);
        assertEquals("dd", result[3]);
        assertEquals("ee", result[4]);
    }

    @Test
    void test_csv_line_contain_comma_in_field() throws Exception {

        String line = "\"Australia\", \"11 Lakkari Cl, Taree, NSW 2430\"";
        String[] result = parser.parseLine(line);

        assertEquals(2, result.length);
        assertEquals("Australia", result[0]);
        assertEquals("11 Lakkari Cl, Taree, NSW 2430", result[1]);

    }

    /*
    //"Australia", "42 Queen Street,
    // New South Wales, 2107"
    @Test
    void test_csv_line_contain_newline_in_field() throws Exception {

        File file = getFileFromTestResourcesFolder("csv/problem2.csv");
        List<List<String>> result = null;//CsvParserMyImpl.read(file);

        // ensure 1 line
        assertEquals(1, result.size());

        // ensure 2 fields
        List<String> list = result.get(0);
        assertEquals(2, list.size());
        assertEquals("Australia", list.get(0));
        // test new line
        assertEquals("42 Queen Street,\n"
                + "New South Wales, 2107", list.get(1));

    }

    // "Australia", "51 Maritime Avenue, West""ern Australia, 6286"
    @Test
    void test_csv_line_contain_double_quote_in_field() throws Exception {

        File file = getFileFromTestResourcesFolder("csv/problem3.csv");
        List<List<String>> result = null;//CsvParserMyImpl.read(file);

        // ensure 1 line
        assertEquals(1, result.size());

        // ensure 2 fields
        List<String> list = result.get(0);
        assertEquals(2, list.size());
        assertEquals("Australia", list.get(0));
        // test single double quote
        assertEquals("51 Maritime Avenue, West\"ern Australia, 6286", list.get(1));

    }

    private File getFileFromTestResourcesFolder(String fileName) throws URISyntaxException {
        // get uri from the test resources folder.
        URL resource = CsvParserMyImplTest.class.getClassLoader().getResource(fileName);
        File file = Paths.get(resource.toURI()).toFile();
        return file;
    }*/
}
