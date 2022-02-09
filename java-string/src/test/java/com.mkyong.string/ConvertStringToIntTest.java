package com.mkyong.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConvertStringToIntTest {

    /*@ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("stringProvider")
    void testMethod(String input, int expected) {
        assertEquals(Integer.parseInt(input), expected);
    }

    static Stream<Arguments> stringProvider() {
        return Stream.of(
                arguments("20", 20),
                arguments("+20", 20),
                arguments("-20", -20)
        );
    }*/

    /*@ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {" 1", "1 ", " 1 ", "+1", "-1", "100", "999", "2147483647"})
    void testStringToNumberValid(String input) {
        assertTrue(StringUtils.isNumeric(input));
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {"10A", "++1", "--1", "1.1", "10-1", "A10", "2147483648"})
    void testStringToNumberInValid(String input) {
        assertFalse(StringUtils.isNumeric(input));
    }*/

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @ValueSource(strings = {"", " "})
    void testBlankIncludeNull3(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @ValueSource(strings = {"", " "})
    void testBlankIncludeNull(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @MethodSource("blankOrNullStrings")
    void testBlankIncludeNull2(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    static Stream<String> blankOrNullStrings() {
        return Stream.of("", " ", null);
    }
}
