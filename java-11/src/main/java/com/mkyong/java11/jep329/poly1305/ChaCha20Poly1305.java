package com.mkyong.java11.jep329.poly1305;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class ChaCha20Poly1305 {

    private static final String ENCRYPT_ALGO = "ChaCha20-Poly1305";
    private static final int NONCE_LEN = 12; // 96 bits, 12 bytes

    // if no nonce, generate a random 12 bytes nonce
    public byte[] encrypt(byte[] pText, SecretKey key) throws Exception {
        return encrypt(pText, key, getNonce());
    }

    public byte[] encrypt(byte[] pText, SecretKey key, byte[] nonce) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        // IV, initialization value with nonce
        IvParameterSpec iv = new IvParameterSpec(nonce);

        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] encryptedText = cipher.doFinal(pText);

        // append nonce to the end of the encrypted text
        byte[] output = ByteBuffer.allocate(encryptedText.length + NONCE_LEN)
                .put(encryptedText)
                .put(nonce)
                .array();

        return output;
    }

    public byte[] decrypt(byte[] cText, SecretKey key) throws Exception {

        ByteBuffer bb = ByteBuffer.wrap(cText);

        // split cText to get the appended nonce
        byte[] encryptedText = new byte[cText.length - NONCE_LEN];
        byte[] nonce = new byte[NONCE_LEN];
        bb.get(encryptedText);
        bb.get(nonce);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        IvParameterSpec iv = new IvParameterSpec(nonce);

        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        // decrypted text
        byte[] output = cipher.doFinal(encryptedText);

        return output;

    }

    // 96-bit nonce (12 bytes)
    private static byte[] getNonce() {
        byte[] newNonce = new byte[12];
        new SecureRandom().nextBytes(newNonce);
        return newNonce;
    }

}
