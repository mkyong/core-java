package com.mkyong.crypto.bytes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// https://stackoverflow.com/questions/5263187/print-an-integer-in-binary-format-in-java
public class ByteSign {

    public static final String OUTPUT_FORMAT = "%32s";

    public static void main(String[] args) {

        System.out.println(Byte.MIN_VALUE);
        System.out.println(Byte.MAX_VALUE);

        String STRING_FORMAT = "%-10s = %s";

        int number = 1;

        System.out.println("Positive Number");
        System.out.println(String.format(STRING_FORMAT, "Input " + number, printBinary(number)));
        System.out.println(String.format(STRING_FORMAT, number + " >> 3", printBinary(number >> 3)));       // sign extension
        System.out.println(String.format(STRING_FORMAT, number + " >>> 3", printBinary(number >>> 3)));     // ignore sign, add leading zero

        int number2 = -1;

        System.out.println("\nNegative Number");
        System.out.println(String.format(STRING_FORMAT, "Input " + number2, printBinary(number2)));
        System.out.println(String.format(STRING_FORMAT, number2 + " >> 2", printBinary(number2 >> 2)));      // sign extension
        System.out.println(String.format(STRING_FORMAT, number2 + " >>> 2", printBinary(number2 >>> 2)));    // ignore sign, add leading zero

    }

    /*public static String printBinaryBitwise(int number, int blockSize, String separator) {

        StringBuilder result = new StringBuilder();

        for (int i = 31; i >= 0; i--) {
            int mask = 1 << i;
            result.append((number & mask) != 0 ? "1" : "0");

            if (i % blockSize == 0)
                result.append(" ");
        }

        return result.toString().trim();
    }*/

    public static String printBinary(int number) {
        return printBinary(number, 8, "|");
    }

    public static String printBinary(int number, int blockSize, String separator) {

        // pad leading zero
        String pBinary = String
                .format(OUTPUT_FORMAT, Integer.toBinaryString(number))
                .replace(" ", "0");

        // split by blockSize
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < pBinary.length()) {
            result.add(pBinary.substring(index, Math.min(index + blockSize, pBinary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }
}
