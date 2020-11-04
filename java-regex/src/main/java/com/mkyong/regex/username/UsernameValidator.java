package com.mkyong.regex.username;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    // simple regex
    //private static final String USERNAME_PATTERN = "^[a-z0-9\\._-]{5,20}$";

    // strict regex
    private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    public static boolean isValid(final String username) {
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

}
