package com.mkyong.crypto.random;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Set;

public class RandomApp {

    public static void main(String[] args) {

        final Set<String> algorithms = Security.getAlgorithms("SecureRandom");

        for (String algorithm : algorithms) {
            System.out.println(algorithm);
        }

        final String defaultAlgorithm = new SecureRandom().getAlgorithm();

        System.out.println("default: " + defaultAlgorithm);


    }
}
