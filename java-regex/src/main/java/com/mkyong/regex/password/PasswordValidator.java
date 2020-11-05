package com.mkyong.regex.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    // digit + lowercase char + uppercase char + punctuation + symbol
    /*private static final String PASSWORD_PATTERN_NOT_SPACE =
            "^(?!.*\\s)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";*/

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
