package com.mkyong.io.howto.resources;

import java.net.URISyntaxException;

public class TestApp {

    public static void main(String[] args) {

        TestApp obj = new TestApp();

        try {

            // Get path of the JAR file
            String jarPath = TestApp.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            System.out.println("JAR Path : " + jarPath);

            // Get name of the JAR file
            String jarName = jarPath.substring(jarPath.lastIndexOf("/") + 1);
            System.out.printf("JAR Name: " + jarName);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
