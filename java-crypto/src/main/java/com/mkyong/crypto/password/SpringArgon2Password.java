package com.mkyong.crypto.password;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class SpringArgon2Password {

    public static void main(String[] args) {

        String password = "Hello World";

        Instant start = Instant.now();

        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();

        String result = encoder.encode(password);

        System.out.println(result);

        Instant end = Instant.now();

        System.out.println(String.format(
                "Hashing took %s ms",
                ChronoUnit.MILLIS.between(start, end)
        ));

        /*Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        Instant start = Instant.now();
        // verify
        String hash = "$argon2id$v=19$m=4096,t=3,p=1$S5U1JtfBZNKQOY7k2QlIhQ$O/xn0thULbuQ94IjFGfrWAZvv7Bi6eqMN9oykWFM4S0";
        if (encoder.matches("Hello World", hash)) {
            System.out.println("match");
        } else {
            System.out.println("not match");
        }

        Instant end = Instant.now();

        System.out.println(String.format(
                "Hashing took %s ms",
                ChronoUnit.MILLIS.between(start, end)
        ));*/

    }

}