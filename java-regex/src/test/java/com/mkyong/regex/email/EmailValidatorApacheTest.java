package com.mkyong.regex.email;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorApacheTest {

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("validEmailProvider")
    void test_email_valid(String email) {
        assertTrue(EmailValidatorApache.isValid(email));
    }

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("invalidEmailProvider")
    void test_email_invalid(String email) {
        assertFalse(EmailValidatorApache.isValid(email));
    }

    // Valid email addresses
    static Stream<String> validEmailProvider() {
        return Stream.of(
                "hello@example.com",                // simple
                "hello@example.co.uk",              // .co.uk, 2 tld
                "hello!#$%&'*+-/=?^_`{|}~2020@example.com",  // many special chars
                "h@example.com",                    // local-part one letter
                "h@example-example.com",            // domain contains a hyphen -
                "h@example-example-example.com",    // domain contains two hyphens - -
                "h@example.example-example.com",    // domain contains . -
                "hello.world-2020@example.com",     // local part contains . -
                "我買@屋企.香港");                   // Support Unicode
    }

    // Invalid email addresses
    static Stream<String> invalidEmailProvider() {
        return Stream.of(
                "hello",                            // email need at least one @
                "hello@2020@example.com",           // email doesn't allow more than one @
                ".hello@example.com",               // local-part can't start with a dot .
                "hello.@example.com",               // local-part can't end with a dot .
                "hello..world@example.com",         // local part don't allow dot . appear consecutively
                // apache supports many special characters
                //"hello!+2020@example.com",          // local-part don't allow special characters like !+
                "hello@example.a",                  // domain tld min 2 chars
                "hello@example..com",               // domain part doesn't allow dot . appear consecutively
                "hello@.com",                       // domain part doesn't start with a dot .
                "hello@.com.",                      // domain part doesn't end with a dot .
                "hello@-example.com",               // domain part doesn't allow to start with a hyphen -
                "hello@example.com-",               // domain part doesn't allow to end with a hyphen -
                "hello@example_example.com",        // domain part doesn't allow underscore
                "1234567890123456789012345678901234567890123456789012345678901234xx@example.com"); // local part is longer than 64 characters
    }

}
