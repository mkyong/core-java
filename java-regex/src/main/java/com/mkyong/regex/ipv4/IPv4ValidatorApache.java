package com.mkyong.regex.ipv4;

import org.apache.commons.validator.routines.InetAddressValidator;

public class IPv4ValidatorApache {

    private static final InetAddressValidator validator = InetAddressValidator.getInstance();

    public static boolean isValid(final String ip) {

        // IPv4
        return validator.isValidInet4Address(ip);

        // IPv4 + IPv6
        // return validator.isValid(ip);

        // IPv6 only
        // return validator.isValidInet6Address(ip);
    }

}
