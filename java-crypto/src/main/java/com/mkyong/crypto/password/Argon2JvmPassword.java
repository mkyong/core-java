package com.mkyong.crypto.password;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

// https://github.com/phxql/argon2-jvm
public class Argon2JvmPassword {

    // default argon2i, salt 16 bytes, hash length 32 bytes.
    private static final Argon2 argon2 = Argon2Factory.create();

    public static void main(String[] args) {

        char[] password = "Hello World".toCharArray();

        Instant start = Instant.now();

        System.out.println(hash(password));

        //String hash = "$argon2i$v=19$m=65536,t=10,p=1$5xRAtFISIp1+vHEhArN5Xg$zlNcgLXXNkhcgAXGhlwpFys0g5mwyI8GVI32zNajSCs";
        //verify(hash, password);

        Instant end = Instant.now();

        System.out.println(String.format(
                "Hashing took %s ms",
                ChronoUnit.MILLIS.between(start, end)
        ));

    }

    public static String hash(char[] password) {
        String result = "";
        try {
            result = argon2.hash(22, 65536, 1, password);
        } finally {
            // Wipe confidential data
            argon2.wipeArray(password);
        }
        return result;
    }

    public static boolean verify(String hash, char[] password) {
        boolean result;
        try {
            if (argon2.verify(hash, password)) {
                result = true;
                System.out.println("Hash matches password.");
            } else {
                result = false;
                System.err.println("Hash doesn't match password.");
            }
        } finally {
            // Wipe confidential data
            argon2.wipeArray(password);
        }
        return result;
    }

}
