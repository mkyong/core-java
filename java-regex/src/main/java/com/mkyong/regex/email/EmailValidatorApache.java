package com.mkyong.regex.email;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidatorApache {

    //doesn't consider local addresses as valid.
    //default, allowLocal = false, allowTld = false
    private static final EmailValidator validator = EmailValidator.getInstance();

    //private static final EmailValidator validator = EmailValidator.getInstance(true);
    //private static final EmailValidator validator = EmailValidator.getInstance(true, true);

    public static boolean isValid(final String email) {
        return validator.isValid(email);
    }

}
