package com.mkyong.regex.date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidatorRegex {

    // ?: match but don't capture it
    // uuuu-M-d
    private static final String DATE_PATTERN =
            "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);

    public static boolean isValid(final String date) {

        boolean result = false;

        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {

            // it is a valid date format yyyy-mm-dd
            // assign true first, later we will check the leap year and odd or even months
            result = true;

            // (?:19|20), match but don't capture it, otherwise it will messy the group order
            // for example, 2020-2-30, it will create 4 groups.
            // group(1) = 2020, group(2) matches (19|20) = 20, group(3) = 2, group(4) = 30
            // So, we put (?:19|20), don't capture this group.
            int year = Integer.parseInt(matcher.group(1));
            // why string? month matches 02 or 2
            String month = matcher.group(2);
            String day = matcher.group(3);

            // 30 or 31 days checking
            // only 1,3,5,7,8,10,12 has 31 days
            if ((month.equals("4") || month.equals("6") || month.equals("9") ||
                    month.equals("04") || month.equals("06") || month.equals("09") ||
                    month.equals("11")) && day.equals("31")) {
                result = false;
            } else if (month.equals("2") || month.equals("02")) {
                if (day.equals("30") || day.equals("31")) {
                    result = false;
                } else if (day.equals("29")) {  // leap year, feb 29 days.
                    if (!isLeapYear(year)) {
                        result = false;
                    }
                }
            }

        }

        return result;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}