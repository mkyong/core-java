package com.mkyong.java11.jep327;

// https://stackoverflow.com/questions/18380901/how-do-i-convert-unicode-codepoints-to-their-character-representation
public class PrintUnicode2 {

    public static void main(String[] args) {

        printCodePoints("U+0041");  // Latin capital A
        printCodePoints("U+20BF");  // Bitcoin icon = ₿
        printCodePoints("U+32C0");  // January in Chinese = ㋀
        printCodePoints("U+1F929"); // emoji 🤩

        printCodePoints("U+4F60");

        // convert Unicode code points
        //String codePoints = "U+20BF";   // Bitcoin icon = ₿

        String codePoints = "U+1F929";   // Bitcoin icon = ₿
        Integer i = Integer.valueOf(codePoints.substring(2), 16);
        System.out.println(i);          // 8383

        // Unicode in UTF-16 encoding
        char[] unicode16 = Character.toChars(i);
        System.out.println(unicode16);

        String s = Character.toString(i);

        // convert Unicode in UTF-16 to Unicode code points
        if(Character.isBmpCodePoint(i)){
            String hex = Integer.toHexString(i);
            System.out.println(String.format("U+%S", hex));
        }else{
            // Supplementary code point
            int supCodePoints = Character.toCodePoint(unicode16[0], unicode16[1]);
            String hex = Integer.toHexString(supCodePoints);
            System.out.println(String.format("U+%S", hex));
        }
    }

    static void printCodePoints(String codePoint) {
        Integer i = Integer.valueOf(codePoint.substring(2), 16);
        char[] chars = Character.toChars(i);
        System.out.println(chars);
    }

}
