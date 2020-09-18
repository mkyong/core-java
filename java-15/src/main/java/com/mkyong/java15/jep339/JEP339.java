package com.mkyong.java15.jep339;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

// https://docs.oracle.com/en/java/javase/15/docs/specs/security/standard-names.html
public class JEP339 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519");
        KeyPair kp = kpg.generateKeyPair();

        byte[] msg = "abc".getBytes(StandardCharsets.UTF_8);

        // algorithm is pure Ed25519
        Signature sig = Signature.getInstance("Ed25519");
        sig.initSign(kp.getPrivate());
        sig.update(msg);
        byte[] s = sig.sign();

        System.out.println(Base64.getEncoder().encodeToString(s));

    }
}
