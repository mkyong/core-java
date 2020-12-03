package com.mkyong.string;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringTokenizerExample {

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer("1, 2, 3, 4, 5");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken().trim());
        }

        /*String line = "This is a String, split by, StringTokenizer example.";
        List<String> result = split(line, ",");
        for (String s : result) {
            System.out.println(s.trim());
        }*/

        /*StringTokenizerExample obj = new StringTokenizerExample();
        List<Trend> trends = obj.readFile(Paths.get("C:\\test\\sample.csv"), "|");
        trends.forEach(System.out::println);*/
    }

    public static List<String> split(String line, String delimiter) {
        List<String> result = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(line, delimiter);
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }

    public List<Trend> readFile(Path path, String delimiter) throws IOException {

        List<Trend> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {

            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, delimiter);
                while (st.hasMoreTokens()) {
                    Integer id = Integer.parseInt(st.nextToken().trim());
                    Double index = Double.parseDouble(st.nextToken().trim());
                    String desc = st.nextToken().trim();
                    result.add(new Trend(id, index, desc));
                }
            }
        }
        return result;
    }

    class Trend {
        private int id;
        private Double index;
        private String desc;

        public Trend(int id, Double index, String desc) {
            this.id = id;
            this.index = index;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "Trend{" +
                    "id=" + id +
                    ", index=" + index +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }
}
