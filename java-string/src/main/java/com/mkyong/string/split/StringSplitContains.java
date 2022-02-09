package com.mkyong.string.split;

public class StringSplitContains {

    public static void main(String[] args) {

        String phone = "012-3456789";

        if (phone.contains("-")) {

            String[] output = phone.split("-");
            System.out.println(output[0]);  // 012
            System.out.println(output[1]);  // 3456789

        } else {
            throw new IllegalArgumentException("String " + phone + " does not contain -");
        }

    }

}
