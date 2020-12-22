package com.mkyong.io.csv.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OpenCsvExample {

    public static void main(String[] args) {

        String fileName = "csv/address.csv";
        InputStream is = OpenCsvExample.class.getClassLoader().getResourceAsStream(fileName);

        CSVReader reader;
        try {
            reader = new CSVReader(new InputStreamReader(is));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println(Arrays.toString(line));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

    }
}
