package com.mkyong.java13.jep354;

public class NewSwitch {

    public static void main(String[] args) {

        System.out.println(getNumber(1));
        System.out.println(getNumber(3));
        System.out.println(getNumberViaCaseL2(6));
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

    // break with value dropped, in favor of yield
    /*private static String getNumberViaBreak(int number) {
        String result = switch (number) {
            case 1, 2:
                break "one or two";
            case 3:
                break "three";
            case 4, 5, 6:
                break "four or five or six";
            default:
                break "unknown";
        };
        return result;
    }*/

    private static String getNumberViaYield(int number) {
        return switch (number) {
            case 1, 2:
                yield "one or two";
            case 3:
                yield "three";
            case 4, 5, 6:
                yield "four or five or six";
            default:
                yield "unknown";
        };
    }

    private static String getNumberViaYield2(int number) {
        return switch (number) {
            case 1, 2:
                yield "one or two";
            case 3:
                yield "three";
            case 4, 5, 6:
                int i = 0;
                i++;
                yield "four or five or six : " + i;
            default:
                yield "unknown";
        };
    }


    private static String getNumberViaCaseL(int number) {
        return switch (number) {
            case 1, 2 -> "one or two";
            case 3 -> "three";
            case 4, 5, 6 -> "four or five or six";
            default -> "unknown";
        };
    }

    private static String getNumberViaCaseL2(int number) {
        return switch (number) {
            case 1, 2 -> "one or two";
            case 3 -> "three";
            case 4, 5, 6 -> {
                int i = 0;
                i++;
                yield "four or five or six :" + 1;
            }
            default -> "unknown";
        };
    }


}
