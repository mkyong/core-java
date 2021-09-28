package com.mkyong.java17.jep406;

public class JEP406_2 {

    public static void main(String[] args) {

        testTriangle(null);
        testTriangle2(null);

    }

    class Shape {}
    class Rectangle extends Shape {}
    class Triangle  extends Shape {
        int calculateArea(){
            return 8;
        } }

    static void testTriangle(Shape s) {
        switch (s) {
            case null:
                break;
            case Triangle t:
                if (t.calculateArea() > 100) {
                    System.out.println("Large triangle");
                    break;
                }else{
                    System.out.println("Triangle");
                }
            default:
                System.out.println("Unknown!");
        }
    }

    static void testTriangle2(Shape s) {
        switch (s) {
            case null ->
                    {}
            case Triangle t && (t.calculateArea() > 100) ->
                    System.out.println("Large triangle");
            case Triangle t ->
                    System.out.println("Triangle");
            default ->
                    System.out.println("Unknown!");
        }
    }

}

