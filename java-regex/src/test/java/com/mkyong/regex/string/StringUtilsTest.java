package com.mkyong.regex.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {

    @ParameterizedTest(name = "#{index} - Run test with input = {0}")
    @MethodSource("validAlphanumericProvider")
    void test_alphanumeric_regex_valid(String input) {
        assertTrue(StringUtils.isAlphanumeric(input));
    }

    static Stream<String> validAlphanumericProvider() {
        return Stream.of(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    @ParameterizedTest(name = "#{index} - Run test with input = {0}")
    @MethodSource("invalidAlphanumericProvider")
    void test_alphanumeric_regex_invalid(String input) {
        assertFalse(StringUtils.isAlphanumeric(input));
    }

    static Stream<String> invalidAlphanumericProvider() {
        return Stream.of(
                "_",                        // underscore
                "@",                        // symbols
                " ",                        // space
                "");                        // empty
    }

}
