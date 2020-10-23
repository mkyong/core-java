package com.mkyong.regex.email;

import com.mkyong.regex.email.EmailValidatorSimple;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorSimpleTest {

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("validEmailProvider")
    void test_email_valid(String email) {
        assertTrue(EmailValidatorSimple.isValid(email));
    }

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("invalidEmailProvider")
    void test_email_invalid(String email) {
        assertFalse(EmailValidatorSimple.isValid(email));
    }

    // Valid email addresses
    static Stream<String> validEmailProvider() {
        return Stream.of(
                "hello@example.com",                // simple
                "hello@example.co.uk",              // .co.uk
                "hello-.+_=#|@example.com",         // special characters
                "h@example.com",                    // local-part one letter
                "h@com",                            // domain one letter
                "我買@屋企.香港"                      // unicode, chinese characters
        );
    }

    // Invalid email addresses
    static Stream<String> invalidEmailProvider() {
        return Stream.of(
                "hello",                            // email need at least one @
                "hello@ "                           // domain cant end with space (whitespace)
        );
    }

}