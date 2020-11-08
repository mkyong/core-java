package com.mkyong.regex.string;

public class StringUtils {

    private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]+$";
    //private static final String NON_ALPHANUMERIC_PATTERN = "^[^a-zA-Z0-9]+$";

    // \\w matches [a-zA-Z0-9] and underscore
    // private static final String ALPHANUMERIC_PATTERN_UNDERSCORE = "^[\\w]+$";
    // private static final String ALPHANUMERIC_PATTERN_UNDERSCORE = "^[a-zA-Z0-9_]+$";

    public static boolean isAlphanumeric(final String input) {
        return input.matches(ALPHANUMERIC_PATTERN);
    }

}
