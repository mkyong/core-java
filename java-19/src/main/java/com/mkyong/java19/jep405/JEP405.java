package com.mkyong.java19.jep405;

public class JEP405 {

    record Point(int x, int y) {
    }

    static void printSum(Object o) {
        if (o instanceof Point p) {
            int x = p.x();  // get x()
            int y = p.y();  // get y()
            System.out.println(x + y);
        }
    }

    static void printSumNew(Object o) {
        if (o instanceof Point(int x,int y)) {  // record pattern
            System.out.println(x + y);
        }
    }

    public static void main(String[] args) {
        printSumNew(new Point(10, 20)); // output 30
    }
}