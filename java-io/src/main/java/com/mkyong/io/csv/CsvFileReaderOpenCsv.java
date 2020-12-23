package com.mkyong.io.csv;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CsvFileReaderOpenCsv implements CsvFileReader {

    @Override
    public List<String[]> read(File file) throws Exception {
        List<String[]> result;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            result = reader.readAll();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

        URL resource = CsvParserSimple.class.getClassLoader().getResource("csv/address.csv");
        File file = Paths.get(resource.toURI()).toFile();

        CsvFileReaderOpenCsv obj = new CsvFileReaderOpenCsv();
        List<String[]> read = obj.read(file);

        read.forEach(x -> System.out.println(Arrays.toString(x)));
    }

}
