package com.mkyong.java12.jep325;

/*
    javac --enable-preview --release 12 Example.java
    java --enable-preview Example
 */
public class SwitchExample {

    public static void main(String[] args) {

        System.out.println(getNumber(1));
        System.out.println(getNumber(3));
        System.out.println(getNumber(6));
        System.out.println(getNumber(10));

    }

    // Traditional switch statement
    private static String getNumber(int number) {
        String result = "";
        switch (number) {
            case 1:
            case 2:
                result = "one or two";
                break;
            case 3:
                result = "three";
                break;
            case 4:
            case 5:
            case 6:
                result = "four or five or six";
                break;
            default:
                result = "unknown";
        }
        ;
        return result;
    }

    // Multiple case labels
    private static String getNumberMultipleCase(int number) {
        String result = "";
        switch (number) {
            case 1, 2:
                result = "one or two";
                break;
            case 3:
                result = "three";
                break;
            case 4, 5, 6:
                result = "four or five or six";
                break;
            default:
                result = "unknown";
        }
        ;
        return result;
    }

    // break with value is dropped in favor of `yield` in Java 13.
    /*private static String getNumberViaBreak(int number) {
        String result = switch (number) {
            case 1:
            case 2:
                break "one or two";
            case 3:
                break "three";
            case 4:
            case 5:
            case 6:
                break "four or five or six";
            default:
                break "unknown";
        };
        return result;
    }*/

    // arrow, label rules, case L
    private static String getNumberViaArrow(int number) {
        String result = switch (number) {
            case 1, 2 -> "one or two";
            case 3 -> "three";
            case 4, 5, 6 -> "four or five or six";
            default -> "unknown";
        };
        return result;
    }

}
