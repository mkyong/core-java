package com.mkyong.string.split;

public class StringSplitMultiDelimiters {

    public static void main(String[] args) {

        String dir = "apple|9|1.88;2.78|0#10";
        String[] output = dir.split("[|;#]");

        System.out.println(output.length);  // 6

        for (String s : output) {
            System.out.println(s);
        }

        /*
        System.out.println(output[0]);      // apple
        System.out.println(output[1]);      // 9
        System.out.println(output[2]);      // 1.88
        System.out.println(output[3]);      // 2.78
        System.out.println(output[4]);      // 0
        System.out.println(output[5]);      // 10
        */

    }

}
