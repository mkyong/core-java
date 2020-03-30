package com.mkyong.java11.jep181;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// https://openjdk.java.net/jeps/181
public class Outer {

    public static class InnerA {
        // access InnerB private method, reflection!
        // Before Java 11 - IllegalAccessException
        // Java 11 - OK
        public void printName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            InnerB obj = new InnerB();
            final Method m = InnerB.class.getDeclaredMethod("printName");
            m.invoke(obj);
        }
    }

    public static class InnerB {
        // private!!!
        private void printName() {
            System.out.println("I'm InnerB!");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        InnerA obj = new InnerA();
        obj.printName();
    }
}
