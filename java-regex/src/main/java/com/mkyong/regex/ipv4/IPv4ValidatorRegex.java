package com.mkyong.regex.ipv4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPv4ValidatorRegex {

    // old code, \\d matches Unicode, for safety, uses [0-9] for ASCII numbers only.
    /*private static final String IPV4_PATTERN_ALLOW_LEADING_ZERO_OLD =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";*/

    // allow leading 0, for example, 01.01.01.01
    /*private static final String IPV4_PATTERN_ALLOW_LEADING_ZERO =
            "^([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\." +
            "([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\." +
            "([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\." +
            "([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])$";*/

    // simple and readable, I think the best ipv4 regex
    /*private static final String IPV4_PATTERN_READABLE =
            "^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$";*/

    // it works the same like IPV4_PATTERN_READABLE, repeat {3} to save typing
    /*private static final String IPV4_PATTERN_REPEAT_3 =
            "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}" +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$";*/

    // it works the same like IPV4_PATTERN_READABLE, shorter version.
    // "\\.(?!$)" ensure ip does not end with a dot
    private static final String IPV4_PATTERN =
            "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";

    // 25[0-5]        = 250-255
    // (2[0-4])[0-9]  = 200-249
    // (1[0-9])[0-9]  = 100-199
    // ([1-9])[0-9]   = 10-99
    // [0-9]          = 0-9
    // (\.(?!$))      = can't end with a dot
    /*private static final String IPV4_PATTERN_SHORTEST =
            "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";*/

    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);

    public static boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
