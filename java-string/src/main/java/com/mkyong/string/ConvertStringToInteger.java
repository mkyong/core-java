package com.mkyong.string;

public class ConvertStringToInteger {

    public static void main(String[] args) {

        /*String number = "99";

        // String to integer
        Integer result = Integer.valueOf(number);

        // 99
        System.out.println(result);*/

        String number = "D99";
        try {
            Integer result = Integer.valueOf(number);
            System.out.println(result);
        } catch (NumberFormatException e) {
            //do something for the exception.
            System.err.println("Invalid number format : " + number);
        }

    }

}