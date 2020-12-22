package com.mkyong.io.csv.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReaderSimple {

    private static final String CSV_SEPARATOR = ",";

    public static void main(String[] args) {

        //readCSVFile("csv/country.csv", CSV_SEPARATOR);
        readCSVFileJava8("csv/address.csv", CSV_SEPARATOR);
    }

    private static void readCSVFileJava8(String fileName, String csvSeparator) {

        String line;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        CSVReaderSimple.class.getClassLoader().getResourceAsStream(fileName)))) {

            while ((line = br.readLine()) != null) {

                // split by a comma separator
                List<String> split = Stream.of(line.split(csvSeparator)).collect(Collectors.toList());

                System.out.println("\nLength : " + split.size());
                split.forEach(System.out::println);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readCSVFile(String fileName, String csvSeparator) {

        String line;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        CSVReaderSimple.class.getClassLoader().getResourceAsStream(fileName)))) {

            while ((line = br.readLine()) != null) {

                // split by a comma separator
                String[] split = line.split(csvSeparator);
                System.out.println("\nLength : " + split.length);
                System.out.println("split[0] : " + split[0]);
                System.out.println("split[1] : " + split[1]);
                System.out.println("split[2] : " + split[2]);
                System.out.println("split[3] : " + split[3]);
                System.out.println("split[4] : " + split[4]);
                System.out.println("split[5] : " + split[5]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readCSVFileReader() {

        String csvFile = "/Users/mkyong/csv/country.csv";
        String line;
        String csvSeparator = ",";

        // auto close file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // split by a comma separator
                String[] split = line.split(csvSeparator);
                System.out.println("\nLength : " + split.length);
                System.out.println("split[0] : " + split[0]);
                System.out.println("split[1] : " + split[1]);
                System.out.println("split[2] : " + split[2]);
                System.out.println("split[3] : " + split[3]);
                System.out.println("split[4] : " + split[4]);
                System.out.println("split[5] : " + split[5]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}