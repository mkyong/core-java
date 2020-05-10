package com.mkyong.java11.jep329.chacha20;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;

/*
The inputs to ChaCha20 encryption, specified by RFC 7539, are:
 - A 256-bit secret key (32 bytes)
 - A 96-bit nonce (12 bytes)
 - A 32-bit initial count (4 bytes)

 abstract class ChaCha20Cipher extends CipherSpi
*/
public class ChaCha20 {

    private static final String ENCRYPT_ALGO = "ChaCha20";

    public byte[] encrypt(byte[] pText, SecretKey key, byte[] nonce, int counter) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        ChaCha20ParameterSpec param = new ChaCha20ParameterSpec(nonce, counter);

        cipher.init(Cipher.ENCRYPT_MODE, key, param);

        byte[] encryptedText = cipher.doFinal(pText);

        return encryptedText;
    }

    public byte[] decrypt(byte[] cText, SecretKey key, byte[] nonce, int counter) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        ChaCha20ParameterSpec param = new ChaCha20ParameterSpec(nonce, counter);

        cipher.init(Cipher.DECRYPT_MODE, key, param);

        byte[] decryptedText = cipher.doFinal(cText);

        return decryptedText;

    }

}
