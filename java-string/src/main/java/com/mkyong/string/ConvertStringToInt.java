package com.mkyong.string;

public class ConvertStringToInt {

    public static void main(String[] args) {

        /*String number = "1";

        // String to int
        int result = Integer.parseInt(number);

        // 1
        System.out.println(result);*/

        String number = "1A";
        try {
            int result = Integer.parseInt(number);
            System.out.println(result);
        } catch (NumberFormatException e) {
            //do something for the exception.
            System.err.println("Invalid number format : " + number);
        }

    }

}