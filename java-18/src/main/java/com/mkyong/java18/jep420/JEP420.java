package com.mkyong.java18.jep420;

public class JEP420 {

    public static void main(String[] args) {

    }

    // java: this case label is dominated by a preceding case label
    /*static void error (Object o){
        switch (o) {
            case CharSequence cs -> System.out.println("A sequence of length " + cs.length());
            case String s ->    // Error - pattern is dominated by previous pattern
                    System.out.println("A string: " + s);
            default -> {
                break;
            }
        }
    }*/

}
