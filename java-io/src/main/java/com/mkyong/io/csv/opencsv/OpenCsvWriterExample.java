package com.mkyong.io.csv.opencsv;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenCsvWriterExample {

    public static void main(String[] args) throws IOException {

        List<String[]> csvData = createCsvDataSpecial();

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (CSVWriter writer = new CSVWriter(new FileWriter("c:\\test\\test.csv"))) {
            writer.writeAll(csvData);
        }

        // custom separator, semicolon ;
        /*try (ICSVWriter writer = new CSVWriterBuilder(
                new FileWriter("c:\\test\\test.csv"))
                .withSeparator(';')
                .build()) {
            writer.writeAll(csvData);
        }*/

    }

    /*private static List<String[]> createCsvDataSimple() {
        String[] header = {"id", "name", "address", "phone"};
        String[] record1 = {"1", "first name", "address 1", "11111"};
        String[] record2 = {"2", "second name", "address 2", "22222"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);

        return list;
    }*/

    private static List<String[]> createCsvDataSpecial() {

        String[] header = {"Make", "Model", "Description", "Price"};
        String[] record1 = {"Dell", "P3421W", "Dell 34, Curved, USB-C Monitor", "2499.00"};
        String[] record2 = {"Dell", "", "Alienware 38 Curved \"Gaming Monitor\"", "6699.00"};
        String[] record3 = {"Samsung", "", "49\" Dual QHD, QLED, HDR1000", "6199.00"};
        String[] record4 = {"Samsung", "", "Promotion! Special Price\n49\" Dual QHD, QLED, HDR1000", "4999.00"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);
        list.add(record3);
        list.add(record4);

        return list;

    }

}
