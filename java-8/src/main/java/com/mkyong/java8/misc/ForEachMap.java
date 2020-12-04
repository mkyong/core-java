package com.mkyong.java8.misc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ForEachMap {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put("D", 40);
        map.put("E", 50);
        map.put("F", 60);

        //loopMapClassic(map);
        loopMapJava8(map);

    }

    public static void loopMapClassic(Map<String, Integer> map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
        }
    }

    public static void loopMapJava8(Map<String, Integer> map) {

        if (map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }

        // lambda
        map.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));

        /*map.forEach(
                (k, v) -> {
                    if (k != null){
                        System.out.println("Key : " + k + ", Value : " + v);
                    }
                }
        );*/

        // Java 8 optional null checking...
        /*Optional
                .ofNullable(map)
                .orElse(Collections.emptyMap())
                .forEach(
                        (k, v) -> System.out.println("Key : " + k + ", Value : " + v)
                );*/

    }
}
