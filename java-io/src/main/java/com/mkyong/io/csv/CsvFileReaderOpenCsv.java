package com.mkyong.io.csv;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
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

}
