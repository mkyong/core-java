package com.mkyong.java11.jep328;

import java.util.concurrent.*;

public class MemoryLeak {

    private static BlockingQueue<byte[]> queue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {

        Runnable producer = () -> {
            while (true) {
                // generates 1mb of object every 10ms
                queue.offer(new byte[1 * 1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    // process every 100ms
                    queue.take();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // give a name, good for profiling
        new Thread(producer, "Producer Thread").start();
        new Thread(consumer, "Consumer Thread").start();

    }

}