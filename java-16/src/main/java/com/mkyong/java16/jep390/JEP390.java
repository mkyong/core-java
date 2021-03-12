package com.mkyong.java16.jep390;

public class JEP390 {

    public static void main(String[] args) {

        Double d = 20.0;
        synchronized (d) {} // javac warning & HotSpot warning
        
    }
}
