package com.mkyong.collections.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class InitMap {

    public static Map<String, String> map5;
    static {
        // mutable Map
        map5 = new HashMap<>();
        map5.put("key1", "value1");
        map5.put("key2", "value2");
        map5.put("key3", "value3");
    }

    public static void main(String[] args) {

        // normal way, mutable Map
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(map.getClass());             // java.util.HashMap

        // immutable Map
        Map<String, String> immutableMap = Collections.unmodifiableMap(map);
        System.out.println(immutableMap.getClass());    // java.util.Collections$UnmodifiableMap
        //immutableMap.put("key4", "value4");             // UnsupportedOperationException

        // @since 1.3
        // single entry immutable Map
        Map<String, String> map1 = Collections.singletonMap("key1", "value1");
        System.out.println(map1.getClass());     // java.util.Collections$SingletonMap
        //map1.put("key2", "value2");            // throws UnsupportedOperationException

        // Java 9, max is 10 entries or elements, immutable map
        Map<String, String> map2 = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        );
        System.out.println(map2.getClass());    // java.util.ImmutableCollections$MapN
        //map2.put("key3", "value4");           // throws UnsupportedOperationException

        // Java 9, no limit of Map entry, immutable Map.
        Map<String, String> map3 = Map.ofEntries(
                Map.entry("key1", "value1"),
                Map.entry("key2", "value2"),
                Map.entry("key3", "value3")
        );
        System.out.println(map3.getClass());    // java.util.ImmutableCollections$MapN
        //map3.put("key3", "value4");             // throws UnsupportedOperationException

        // It works for all Java version, mutable Map.
        Map<String, String> map4 = createMap();
        System.out.println(map4.getClass()); // java.util.HashMap
        map4.put("key4", "value4");          // no problem.

        // static init
        System.out.println(map5.getClass()); // java.util.HashMap
        map5.put("key4", "value4");          // no problem.*/

        // Java 8, mutable Map, do not use this, just for reference
        /*Map<String, String> map6 = Stream.of(
                new AbstractMap.SimpleEntry<>("key1", "value1"),
                new AbstractMap.SimpleEntry<>("key2", "value2"),
                new AbstractMap.SimpleEntry<>("key3", "value3"))
                .collect(
                        toMap(AbstractMap.SimpleEntry::getKey,
                                AbstractMap.SimpleEntry::getValue)
                );
        System.out.println(map6.getClass()); // java.util.HashMap
        map6.put("key4", "value4");          // no problem.*/

        // classic way, loop a Map
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }

    }

    private static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        return map;
    }

}
