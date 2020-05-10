package com.mkyong.java11.jep329.chacha20v2;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import java.nio.ByteBuffer;

public class ChaCha20v2 {

    private static final String ENCRYPT_ALGO = "ChaCha20";

    private static final int LEN_NONCE = 12;
    private static final int LEN_COUNTER = 4;

    public byte[] encrypt(byte[] pText, SecretKey key, byte[] nonce, int counter) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        ChaCha20ParameterSpec param = new ChaCha20ParameterSpec(nonce, counter);

        cipher.init(Cipher.ENCRYPT_MODE, key, param);

        byte[] encryptedText = cipher.doFinal(pText);

        // append nonce + count
        byte[] output = new byte[encryptedText.length + LEN_NONCE + LEN_COUNTER];

        System.arraycopy(encryptedText, 0, output, 0, encryptedText.length);
        System.arraycopy(nonce, 0, output, encryptedText.length, LEN_NONCE);

        // convert int to byte[]
        byte[] counterByteArray = ByteBuffer.allocate(4).putInt(counter).array();
        System.arraycopy(counterByteArray, 0, output, encryptedText.length + LEN_NONCE, LEN_COUNTER);

        return output;
    }

    public byte[] decrypt(byte[] cText, SecretKey key) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        // get only the encrypted text
        byte[] encryptedText = new byte[cText.length - (LEN_NONCE + LEN_COUNTER)];
        System.arraycopy(cText, 0, encryptedText, 0, cText.length - (LEN_NONCE + LEN_COUNTER));

        // get nonce
        byte[] nonce = new byte[12];
        System.arraycopy(cText, encryptedText.length, nonce, 0, LEN_NONCE);

        // get initial counter
        byte[] counter = new byte[4];
        System.arraycopy(cText, encryptedText.length + LEN_NONCE, counter, 0, LEN_COUNTER);

        /// convert byte array to int
        int ic = ByteBuffer.wrap(counter).getInt();
        ChaCha20ParameterSpec param = new ChaCha20ParameterSpec(nonce, ic);

        cipher.init(Cipher.DECRYPT_MODE, key, param);

        byte[] decryptedText = cipher.doFinal(encryptedText);

        return decryptedText;

    }

}
