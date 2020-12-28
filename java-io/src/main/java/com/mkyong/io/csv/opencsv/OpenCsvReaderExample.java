package com.mkyong.io.csv.opencsv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class OpenCsvReaderExample {

    public static void main(String[] args) throws IOException, CsvException, URISyntaxException {

        /*String fileName = "c:\\test\\csv\\country.csv";
        List<String[]> r = readCsvFile(fileName);
        r.forEach(x -> System.out.println(Arrays.toString(x)));*/

        readCsvWithEmbeddedSpecial();

    }

    private static void readCsvWithEmbeddedSpecial() throws IOException, CsvException, URISyntaxException {

        // loads file from resource folder
        URL resource = OpenCsvReaderExample.class.getClassLoader().getResource("csv/monitor.csv");
        File file = Paths.get(resource.toURI()).toFile();

        List<String[]> r;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            r = reader.readAll();
        }

        // print result
        int listIndex = 0;
        for (String[] arrays : r) {
            System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays));

            int index = 0;
            for (String array : arrays) {
                System.out.println(index++ + " : " + array);
            }

        }

    }

    private static List<Country> readCsvToObject(String fileName) throws IOException, CsvException {
        List<Country> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Country.class)
                .build()
                .parse();
        return beans;
    }

    private static List<String[]> readCsvFileCustomSeparator(String fileName, char separator) throws IOException, CsvException {

        List<String[]> r;

        // custom separator
        CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).build();
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()) {
            r = reader.readAll();
        }
        return r;

    }

    private static List<String[]> readCsvFile(String fileName) throws IOException, CsvException {

        List<String[]> r;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            r = reader.readAll();
        }

        // read line by line
        /*try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                r.add(lineInArray);
            }
        }*/

        return r;

    }

}
