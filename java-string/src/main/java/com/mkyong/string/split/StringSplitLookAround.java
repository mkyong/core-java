package com.mkyong.string.split;

public class StringSplitLookAround {

    public static void main(String[] args) {

        String phone = "012-3456789";

        if (phone.contains("-")) {

            // positive lookahead, split char at right-hand side
            String[] output = phone.split("(?=-)");
            System.out.println(output[0]);  // 012
            System.out.println(output[1]);  // -3456789

            // positive lookbehind, split char at left-hand side
            String[] output2 = phone.split("(?<=-)");
            System.out.println(output2[0]);  // 012-
            System.out.println(output2[1]);  // 3456789

        } else {
            throw new IllegalArgumentException("String " + phone + " does not contain -");
        }

    }

}
