package com.mkyong.string;

public class ConvertStringToInt2 {

    public static void main(String[] args) {

        // 2147483647
        System.out.println(Integer.MAX_VALUE);

        String number = "2147483648";
        try {
            int result = Integer.parseInt(number);
            System.out.println(result);
        } catch (NumberFormatException e) {
            //do something for the exception.
            System.err.println("Invalid number format : " + number);
        }

    }

}