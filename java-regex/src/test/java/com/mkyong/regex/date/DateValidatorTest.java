package com.mkyong.regex.date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidatorTest {

    @ParameterizedTest(name = "#{index} - Run test with date = {0}")
    @MethodSource("validDateProvider")
    void test_date_regex_valid(String date) {
        assertTrue(DateValidatorRegex.isValid(date));
    }

    @ParameterizedTest(name = "#{index} - Run test with date = {0}")
    @MethodSource("invalidDateProvider")
    void test_date_regex_invalid(String date) {
        assertFalse(DateValidatorRegex.isValid(date));
    }

    /*@ParameterizedTest(name = "#{index} - Run test with date = {0}")
    @MethodSource("validDateProvider")
    void test_date_java_api_valid(String date) {
        assertTrue(DateValidatorDateTimeFormatter.isValid(date));
    }

    @ParameterizedTest(name = "#{index} - Run test with date = {0}")
    @MethodSource("invalidDateProvider")
    void test_date_java_api_invalid(String date) {
        assertFalse(DateValidatorDateTimeFormatter.isValid(date));
    }*/

    static Stream<String> validDateProvider() {
        return Stream.of(
                "1998-09-30",
                "1998-9-30",
                "2020-09-1",
                "2020-09-01",
                "2020-9-1",
                "2020-9-01",
                "2020-2-29",             // leap year
                "2020-2-28",             // leap year
                "2019-2-28",             // common year
                "2000-02-29",            // 2000 is a leap year, % 400 == 0
                "1900-02-28",            // 1900 is a common year
                "2020-07-31",
                "2020-08-31",
                "2020-06-30",
                "1900-01-01",
                "2099-12-31");
    }

    static Stream<String> invalidDateProvider() {
        return Stream.of(
                "1998-09-31",               // invalid day, sep max 30
                "1998-11-31",               // invalid day, nov max 30
                "2008-02-2x",               // invalid day 2x
                "2008-0x-28",               // invalid month 0x
                "20xx-02-28",               // invalid year 20xx
                "20-11-02",                 // invalid year 20, must be yyyy
                "2020/11/02",               // invalid date format, yyyy-mm-dd
                "2020-11-32",               // invalid day, 32
                "2020-13-30",               // invalid month 13
                "2020-A-20",                // invalid month A
                "2020-2-30",                // leap year, feb max 29
                "2019-2-29",                // common year, feb max 28
                "1900-02-29",               // 1900 is a common year, feb max 28
                "12012-04-05",              // support only 4 digits years
                " ",                        // empty
                "");                        // empty
    }

}