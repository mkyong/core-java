package com.mkyong.regex.hex;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HexValidatorWebColorTest {

    @ParameterizedTest(name = "#{index} - Run test with web color = {0}")
    @MethodSource("validWebColorProvider")
    void test_color_regex_valid(String color) {
        assertTrue(HexValidatorWebColor.isValid(color));
    }

    @ParameterizedTest(name = "#{index} - Run test with web color = {0}")
    @MethodSource("invalidWebColorProvider")
    void test_color_regex_invalid(String color) {
        assertFalse(HexValidatorWebColor.isValid(color));
    }

    static Stream<String> validWebColorProvider() {
        return Stream.of(
                "#000000",
                "#999999",
                "#1a1a1a",
                "#1A1A1A",
                "#0f0f0f",
                "#0F0F0F",
                "#bcbcbc",
                "#BcbCbC",
                "#000",
                "#FFF",
                "#abc",
                "#def");
    }

    static Stream<String> invalidWebColorProvider() {
        return Stream.of(
                "123456",                   // must start with a #
                "#afafah",                  // support a-f only, h is not allowed
                "#123abce",                 // invalid length, must length of 3 or 6
                "#1234",                    // invalid length, must length of 3 or 6
                "#-123",                    // support 0-9 only
                " ",                        // space
                "");                        // empty
    }

}