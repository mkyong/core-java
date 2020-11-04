package com.mkyong.regex.username;

public class UsernameValidatorCode {

    private static final char[] SUPPORT_SYMBOLS_CHAR = {'.', '_', '-'};

    public static boolean isValid(final String username) {

        // check empty
        if (username == null || username.length() == 0) {
            return false;
        }

        // check length
        if (username.length() < 5 || username.length() > 20) {
            return false;
        }

        return isValidUsername(username.toCharArray());
    }

    private static boolean isValidUsername(final char[] username) {

        int currentPosition = 0;
        boolean valid = true;

        // check char by char
        for (char c : username) {

            // if alphanumeric char, no need check, process next
            if (!Character.isLetterOrDigit(c)) {

                // for non-alphanumeric char, also not a supported symbol, break
                if (!isSupportedSymbols(c)) {
                    valid = false;
                    break;
                }

                // ensures first and last char not a supported symbol
                if (username[0] == c || username[username.length - 1] == c) {
                    valid = false;
                    break;
                }

                // ensure supported symbol does not appear consecutively
                // is next position also a supported symbol?
                if (isSupportedSymbols(username[currentPosition + 1])) {
                    valid = false;
                    break;
                }

            }

            currentPosition++;
        }

        return valid;

    }

    private static boolean isSupportedSymbols(final char symbol) {
        for (char temp : SUPPORT_SYMBOLS_CHAR) {
            if (temp == symbol) {
                return true;
            }
        }
        return false;
    }

    /*private static boolean isAlphanumeric(final char[] username) {
        for (char c : username) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }*/

}