package com.mkyong.string.split;

import java.util.StringTokenizer;

// This `StringTokenizer` is a legacy class, retained for compatibility reasons;
// the use is discouraged! Please upgrade the code to `String#split()`.
public class StringSplitStringTokenizer {

    public static void main(String[] args) {

        String test = "abc.def.123";
        // the delimiter is a string, not regex, no need to escape the dot
        StringTokenizer token = new StringTokenizer(test, ".");

        while (token.hasMoreTokens()) {
            System.out.println(token.nextToken());
        }

    }

}