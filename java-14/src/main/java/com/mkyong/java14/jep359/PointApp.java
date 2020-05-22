package com.mkyong.java14.jep359;

public class PointApp {

    public static void main(String[] args) {

        // constructor Point(int x, int y)
        Point p1 = new Point(10, 20);
        // getters int x()
        System.out.println(p1.x());         // 10
        // getters int y()
        System.out.println(p1.y());         // 20

        Point p2 = new Point(11, 22);
        System.out.println(p2.x());         // 11
        System.out.println(p2.y());         // 22

        Point p3 = new Point(10, 20);
        System.out.println(p3.x());         // 10
        System.out.println(p3.y());         // 20

        // hashCode and equals
        System.out.println(p1.hashCode());  // 330
        System.out.println(p2.hashCode());  // 363
        System.out.println(p3.hashCode());  // 330

        System.out.println(p1.equals(p2));  // false
        System.out.println(p1.equals(p3));  // true
        System.out.println(p1.equals(p1));  // true

        // toString()
        System.out.println(p1);             // Point[x=10, y=20]
        System.out.println(p2);             // Point[x=11, y=22]
        System.out.println(p3);             // Point[x=10, y=20]

    }

}
