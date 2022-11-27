package com.mkyong.java19.jep425;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class JEP425 {

    public static void main(String[] args) {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }  // executor.close() is called implicitly, and waits

        /*try (var executor = Executors.newFixedThreadPool(20)) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }*/

        System.out.println("Done");

    }
}
