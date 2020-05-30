package com.mkyong.crypto.encryptor;

import com.mkyong.crypto.utils.CryptoUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * AES-CBC inputs - 16 bytes IV, need the same IV and secret keys for encryption and decryption.
 * <p>
 * The output consist of iv, encrypted content and auth tag in the following format:
 * output = byte[] {i i i c c c c c c ...}
 * <p>
 * i = IV bytes
 * c = content bytes (encrypted content)
 */
public class EncryptorAesCbc {

    // AES-CBC, IvParameterSpec, 16 bytes IV
    private static final String ENCRYPT_ALGO = "AES/CBC/PKCS5Padding";
    private static final int IV_LENGTH_BYTE = 16;
    private static final int AES_KEY_BIT = 256;

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    // AES-CBC needs IvParameterSpec
    public static byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText;

    }

    // prefix IV length + IV bytes to cipher text
    public static byte[] encryptWithPrefixIV(byte[] pText, SecretKey secret, byte[] iv) throws Exception {

        byte[] cipherText = encrypt(pText, secret, iv);

        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
                .put(iv)
                .put(cipherText)
                .array();
        return cipherTextWithIv;

    }

    public static String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        byte[] plainText = cipher.doFinal(cText);
        return new String(plainText, UTF_8);

    }

    public static String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws Exception {

        ByteBuffer bb = ByteBuffer.wrap(cText);

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        //bb.get(iv, 0, iv.length);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        String plainText = decrypt(cipherText, secret, iv);
        return plainText;

    }

    public static void main(String[] args) throws Exception {

        String OUTPUT_FORMAT = "%-30s:%s";

        String pText = "Hello World AES-CBC";

        // encrypt and decrypt need the same key.
        // get AES 256 bits (32 bytes) key
        SecretKey secretKey = CryptoUtils.getAESKey(AES_KEY_BIT);

        // encrypt and decrypt need the same IV.
        // AES-CBC needs IV 128-bit (16 bytes)
        byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);

        byte[] encryptedText = EncryptorAesCbc.encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);

        System.out.println("\n------ AES CBC Encryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));
        System.out.println(String.format(OUTPUT_FORMAT, "IV  (hex)", CryptoUtils.hex(iv)));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) ", CryptoUtils.hex(encryptedText)));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));

        System.out.println("\n------ AES CBC Decryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex)", CryptoUtils.hex(encryptedText)));
        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));
        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));

        String decryptedText = EncryptorAesCbc.decryptWithPrefixIV(encryptedText, secretKey);

        System.out.println(String.format(OUTPUT_FORMAT, "Decrypted (plain text)", decryptedText));

    }

}
