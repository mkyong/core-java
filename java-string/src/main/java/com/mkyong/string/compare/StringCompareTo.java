package com.mkyong.string.compare;

public class StringCompareTo {

    public static void main(String[] args) {

        System.out.println("-Negative Number-");
        System.out.println("a".compareTo("b")); // -1
        System.out.println("a".compareTo("c")); // -2
        System.out.println("a".compareTo("d")); // -3
        System.out.println("a".compareTo("e")); // -4

        System.out.println("1".compareTo("2")); // -1
        System.out.println("1".compareTo("3")); // -2
        System.out.println("1".compareTo("4")); // -3
        System.out.println("1".compareTo("5")); // -4

        System.out.println("-Positive Number-");
        System.out.println("b".compareTo("a")); // 1
        System.out.println("c".compareTo("a")); // 2
        System.out.println("d".compareTo("a")); // 3
        System.out.println("e".compareTo("a")); // 4

        System.out.println("2".compareTo("1")); // 1
        System.out.println("3".compareTo("1")); // 2
        System.out.println("4".compareTo("1")); // 3
        System.out.println("5".compareTo("1")); // 4

        System.out.println("-Zero-");
        System.out.println("a".compareTo("a")); // 0
        System.out.println("1".compareTo("1")); // 0

        System.out.println("-Vary Length-");
        System.out.println("a".compareTo("ab"));    // -1
        System.out.println("a".compareTo("abc"));   // -2
        System.out.println("a".compareTo("abcd"));  // -3

        System.out.println("11".compareTo("112"));    // -1
        System.out.println("11".compareTo("1123"));   // -2
        System.out.println("11".compareTo("11234"));  // -3

        System.out.println("-compareToIgnoreCase-");
        System.out.println("a".compareTo("A")); // 32
        System.out.println("a".compareToIgnoreCase("A")); // 0

    }

}
