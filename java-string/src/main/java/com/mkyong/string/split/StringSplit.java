package com.mkyong.string.split;

public class StringSplit {

    public static void main(String[] args) {

        String phone = "012-3456789";
        String[] output = phone.split("-");

        System.out.println(output.length);  // 2

        String part1 = output[0];   // 012
        String part2 = output[1];   // 3456789

        System.out.println(part1);  // 012
        System.out.println(part2);  // 3456789

    }

}
