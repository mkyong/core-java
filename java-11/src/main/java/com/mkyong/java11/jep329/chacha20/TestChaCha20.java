package com.mkyong.java11.jep329.chacha20;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TestChaCha20 {

    public static void main(String[] args) throws Exception {

        String input = "Java & ChaCha20 encryption example.";

        ChaCha20 cipher = new ChaCha20();

        SecretKey key = getKey();           // 256-bit secret key (32 bytes)
        byte[] nonce = getNonce();          // 96-bit nonce (12 bytes)
        int counter = 1;                    // 32-bit initial count (8 bytes)

        System.out.println("Input          : " + input);
        System.out.println("Input     (hex): " + convertBytesToHex(input.getBytes()));

        System.out.println("\n---Encryption---");
        byte[] cText = cipher.encrypt(input.getBytes(), key, nonce, counter);   // encrypt

        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Nonce     (hex): " + convertBytesToHex(nonce));
        System.out.println("Counter        : " + counter);
        System.out.println("Encrypted (hex): " + convertBytesToHex(cText));

        System.out.println("\n---Decryption---");

        byte[] pText = cipher.decrypt(cText, key, nonce, counter);              // decrypt

        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Nonce     (hex): " + convertBytesToHex(nonce));
        System.out.println("Counter        : " + counter);
        System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted      : " + new String(pText));

        /*byte[] pText = cipher.decrypt(cText, key);              // decrypt
        System.out.println("Key   (hex)    : " + convertBytesToHex(key.getEncoded()));
        System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted      : " + new String(pText));*/

    }

    // https://mkyong.com/java/java-how-to-convert-bytes-to-hex/
    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }

    // A 256-bit secret key (32 bytes)
    private static SecretKey getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
        keyGen.init(256, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }

    // 96-bit nonce (12 bytes)
    private static byte[] getNonce() {
        byte[] newNonce = new byte[12];
        new SecureRandom().nextBytes(newNonce);
        return newNonce;
    }

}
