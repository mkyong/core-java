package com.mkyong.crypto.password;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PasswordArgon2SpringSecurity {

    public static void main(String[] args) {

        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 65536, 10);
        //Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();

        String password = "Hello World";

        Instant start = Instant.now();  // start timer

        String hash = encoder.encode(password);
        System.out.println(hash);

        // argon2 verify hash
        /*if (encoder.matches("Hello World", hash)) {
            System.out.println("match");
        }*/

        Instant end = Instant.now();    // end timer

        System.out.println(String.format(
                "Hashing took %s ms",
                ChronoUnit.MILLIS.between(start, end)
        ));

    }

}