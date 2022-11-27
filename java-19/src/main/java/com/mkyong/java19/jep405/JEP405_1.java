package com.mkyong.java19.jep405;

public class JEP405_1 {
    
    record Point(int x, int y) {
    }

    record Total(Point p1, Point p2) {
    }

    static void printSum(Object o) {
        // record nested pattern
        if (o instanceof Total(Point(int x,int y),Point(int x2,int y2))) {
            System.out.println(x + y + x2 + y2);
        }
    }

    public static void main(String[] args) {

        printSum(new Total(new Point(10, 5), new Point(2, 3)));

    }
}
