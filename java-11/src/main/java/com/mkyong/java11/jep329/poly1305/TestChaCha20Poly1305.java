package com.mkyong.java11.jep329.poly1305;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TestChaCha20Poly1305 {

    private static final int NONCE_LEN = 12;                    // 96 bits, 12 bytes
    private static final int MAC_LEN = 16;                      // 128 bits, 16 bytes

    public static void main(String[] args) throws Exception {

        String input = "Java & ChaCha20-Poly1305.";

        ChaCha20Poly1305 cipher = new ChaCha20Poly1305();

        SecretKey key = getKey();                               // 256-bit secret key (32 bytes)

        System.out.println("Input                  : " + input);
        System.out.println("Input             (hex): " + convertBytesToHex(input.getBytes()));

        System.out.println("\n---Encryption---");
        byte[] cText = cipher.encrypt(input.getBytes(), key);   // encrypt

        System.out.println("Key               (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Encrypted         (hex): " + convertBytesToHex(cText));

        System.out.println("\n---Print Mac and Nonce---");

        ByteBuffer bb = ByteBuffer.wrap(cText);

        // This cText contains chacha20 ciphertext + polu1305 MAC + nonce

        // ChaCha20 encrypted the plaintext into a ciphertext of equal length
        byte[] originalCText = new byte[input.getBytes().length];
        byte[] nonce = new byte[NONCE_LEN];     // 16 bytes , 128 bits
        byte[] mac = new byte[MAC_LEN];         // 12 bytes , 96 bits

        bb.get(originalCText);
        bb.get(mac);
        bb.get(nonce);

        System.out.println("Cipher (original) (hex): " + convertBytesToHex(originalCText));
        System.out.println("MAC               (hex): " + convertBytesToHex(mac));
        System.out.println("Nonce             (hex): " + convertBytesToHex(nonce));

        System.out.println("\n---Decryption---");
        System.out.println("Input             (hex): " + convertBytesToHex(cText));

        byte[] pText = cipher.decrypt(cText, key);              // decrypt

        System.out.println("Key               (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Decrypted         (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted              : " + new String(pText));

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

}
