package com.mkyong.io.csv;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFileReaderOpenCsvTest {

    private CsvFileReader csvReader = new CsvFileReaderOpenCsv();

    // "Australia", "11 Lakkari Cl, Taree, NSW 2430"
    @Test
    void test_csv_line_contain_comma_in_field() throws Exception {

        File file = getFileFromTestResourcesFolder("csv/problem1.csv");
        List<String[]> result = csvReader.read(file);

        // ensure 1 line
        assertEquals(1, result.size());

        // ensure 2 fields
        String[] a = result.get(0);
        assertEquals(2, a.length);
        assertEquals("Australia", a[0]);
        assertEquals("11 Lakkari Cl, Taree, NSW 2430", a[1]);

    }

    //"Australia", "42 Queen Street,
    // New South Wales, 2107"
    @Test
    void test_csv_line_contain_newline_in_field() throws Exception {

        File file = getFileFromTestResourcesFolder("csv/problem2.csv");
        List<String[]> result = csvReader.read(file);

        // ensure 1 line
        assertEquals(1, result.size());

        // ensure 2 fields
        String[] a = result.get(0);
        assertEquals(2, a.length);
        assertEquals("Australia", a[0]);
        // test new line
        assertEquals("42 Queen Street,\n"
                + "New South Wales, 2107", a[1]);

    }

    // "Australia", "51 Maritime Avenue, West""ern Australia, 6286"
    @Test
    void test_csv_line_contain_double_quote_in_field() throws Exception {

        File file = getFileFromTestResourcesFolder("csv/problem3.csv");
        List<String[]> result = csvReader.read(file);

        // ensure 1 line
        assertEquals(1, result.size());

        // ensure 2 fields
        String[] a = result.get(0);
        assertEquals(2, a.length);
        assertEquals("Australia", a[0]);
        // test single double quote
        assertEquals("51 Maritime Avenue, West\"ern Australia, 6286", a[1]);

    }

    private File getFileFromTestResourcesFolder(String fileName) throws URISyntaxException {
        // get uri from the test resources folder.
        URL resource = CsvFileReaderOpenCsvTest.class.getClassLoader().getResource(fileName);
        File file = Paths.get(resource.toURI()).toFile();
        return file;
    }
}
