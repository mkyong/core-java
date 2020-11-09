package com.mkyong.regex.hex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexValidatorWebColor {

    private static final String HEX_WEBCOLOR_PATTERN
            = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";

    private static final Pattern pattern = Pattern.compile(HEX_WEBCOLOR_PATTERN);

    public static boolean isValid(final String colorCode) {
        Matcher matcher = pattern.matcher(colorCode);
        return matcher.matches();
    }

    public static void main(String[] args) {

        String str = "p { color: #000111; }";
        Pattern pattern = Pattern.compile(
                "^(.*?)(#([a-fA-F0-9]{6}|[a-fA-F0-9]{3}))\\b(.*)$");

        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());       // 4
            System.out.println(matcher.group(0));           // p { color: #000; }
            System.out.println(matcher.group(1));           // p { color:
            System.out.println(matcher.group(2));           // #000
            System.out.println(matcher.group(3));           // 000
            System.out.println(matcher.group(4));           // ; }
        } else {
            System.out.println("no match!");
        }

    }

}