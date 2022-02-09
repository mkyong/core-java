package com.mkyong.string;

import java.util.Optional;

public class ConvertStringToIntegerJava8 {

    public static void main(String[] args) {

        String number = "99";

        Optional<Integer> result = convertStringToInteger(number);

        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.err.println("Unable to convert the number : " + number);
        }

    }

    private static Optional<Integer> convertStringToInteger(String input) {

        if (input == null) return Optional.empty();

        input = input.trim();

        if (input.isEmpty()) return Optional.empty();

        try {
            return Optional.of(Integer.valueOf(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

    }

}