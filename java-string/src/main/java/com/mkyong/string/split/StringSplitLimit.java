package com.mkyong.string.split;

public class StringSplitLimit {

    public static void main(String[] args) {

        String phone = "012-345-678-9";
        String[] output = phone.split("-", 3);

        System.out.println(output.length);  // 3

        String part1 = output[0];   // 012
        String part2 = output[1];   // 345
        String part3 = output[2];   // 678-9

        System.out.println(part1);
        System.out.println(part2);
        System.out.println(part3);

    }

}
