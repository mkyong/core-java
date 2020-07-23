package com.mkyong.crypto.bytes;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;

// https://mkyong.com/java/java-how-to-convert-bytes-to-hex/
// https://mkyong.com/java/how-to-convert-hex-to-ascii-in-java/
// https://stackoverflow.com/questions/2817752/java-code-to-convert-byte-to-hexadecimal
// https://stackoverflow.com/questions/332079/in-java-how-do-i-convert-a-byte-array-to-a-string-of-hex-digits-while-keeping-l/2197650#2197650
public class BytesUtils {

    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    // apache common codec
    public static String encode_cc(byte[] bytes) {
        char[] result = Hex.encodeHex(bytes);
        return new String(result);
    }

    public static String encode_string_format(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    public static String encode_integer(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            int decimal = (int) aByte & 0xff;               // bytes widen to int, need mask, prevent sign extension
            // get last 8 bits
            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {                    // pad with zero, e.g \t
                hex = "0" + hex;
            }
            result.append(hex);
        }
        return result.toString();
    }

    public static String encode_bitwise(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];

        int j = 0;
        for (byte aByte : bytes) {

            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & aByte) >>> 4];    // 0xf0 = FFFF 0000
            // Bottom 4
            result[j++] = HEX[(0x0F & aByte)];          // 0x0F = 0000 FFFF
        }

        return new String(result);
    }

    public static byte[] decode(CharSequence s) {
        int nChars = s.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException(
                    "Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];  // 2 hex = 1 letter

        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16); // convert base16 (hex) to int
            int lsb = Character.digit(s.charAt(i + 1), 16);

            System.out.println("msb:" + msb);
            System.out.println("lsb:" + lsb);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException(
                        "Detected a Non-hex character at " + (i + 1) + " or " + (i + 2) + " position");
            }
            result[i / 2] = (byte) ((msb << 4) | lsb); //msb + lsb
        }
        return result;
    }

    public static void main(String[] args) {

        String OUTPUT_FORMAT = "%-30s:%s";

        //String input = "\t\n"; // test padding
        //String input = "I Love Java";
        String input = "a";

        //System.out.println(Integer.toBinaryString(Integer.parseInt(input)));

        System.out.println(String.format(OUTPUT_FORMAT, "input", input));

        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);

        String hexApache = BytesUtils.encode_cc(bytes);
        System.out.println(String.format(OUTPUT_FORMAT, "Result (Apache Common Codec)", hexApache));

        String hexStringFormat = BytesUtils.encode_string_format(bytes);
        System.out.println(String.format(OUTPUT_FORMAT, "Result (String.format)", hexStringFormat));

        String hexInteger = BytesUtils.encode_integer(bytes);
        System.out.println(String.format(OUTPUT_FORMAT, "Result (Integer.toHexString)", hexInteger));

        String hexBitwise = BytesUtils.encode_bitwise(bytes);
        System.out.println(String.format(OUTPUT_FORMAT, "Result (encode_bitwise)", hexBitwise));

        System.out.println(String.format(OUTPUT_FORMAT, "Result (decode)", new String(BytesUtils.decode(hexBitwise))));


        int charCode = Integer.parseInt("01100001", 2);
        System.out.println(charCode);   // 97, look ascii table
        String str = Character.toString((char) charCode);
        System.out.println(str);

    }
}
