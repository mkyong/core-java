package com.mkyong.io.howto;

public class GetNewLine {

    public static void main(String[] args) {

        System.out.print("Line 1");
        System.out.print(System.lineSeparator());
        System.out.print("Line 2");

        /*System.out.print("Line 1");
        System.out.print(System.getProperty("line.separator"));
        System.out.print("Line 2");*/

        //System.out.print(String.format("%s%n%s", "Line 1", "Line 2"));

        // write a new line character
        /*String fileName = "/home/mkyong/sample.txt";

        List<String> content = Arrays.asList("A", "B", "C");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (String t : content) {
                bw.write(t);
                bw.newLine();
                //bw.write(System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
