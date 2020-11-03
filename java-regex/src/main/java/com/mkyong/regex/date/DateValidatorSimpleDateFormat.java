package com.mkyong.regex.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidatorSimpleDateFormat {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");

    public static boolean isValid(final String date) {

        boolean valid = false;

        try {
            // why 2008-02-2x, 20-11-02, 12012-04-05 are valid date?
            sdf.parse(date);
            // strict mode - check 30 or 31 days, leap year
            sdf.setLenient(false);
            valid = true;

        } catch (ParseException e) {
            e.printStackTrace();
            valid = false;
        }

        return valid;
    }
}
