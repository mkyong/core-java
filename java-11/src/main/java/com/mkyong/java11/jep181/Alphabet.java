package com.mkyong.java11.jep181;

import java.util.Arrays;

// https://openjdk.java.net/jeps/181
public class Alphabet {

    private String name = "I'm Alphabet!";

    public class A {
        public void printName() {
            System.out.println(name);       // access Alphabet's private member!
        }
    }

    public class B {
        public void printName() {
            System.out.println(name);       // access Alphabet's private member!
        }

        public class B1 {
            public void printName() {
                System.out.println(name);   // access Alphabet's private member!
            }
        }
    }

    public static void main(String[] args) {

        A objA = new Alphabet().new A();
        objA.printName();

        B objB = new Alphabet().new B();
        objB.printName();

        B.B1 objB1 = new Alphabet().new B().new B1();
        objB1.printName();

        System.out.println(Alphabet.class.getNestHost());       // Alphabet
        System.out.println(A.class.getNestHost());              // Alphabet
        System.out.println(B.class.getNestHost());              // Alphabet
        System.out.println(B.B1.class.getNestHost());           // Alphabet!, not B

        System.out.println("---");

        System.out.println(Arrays.toString(Alphabet.class.getNestMembers()));   // Alphabet, Alphabet$A, Alphabet$B, Alphabet$B$B1
        System.out.println(Arrays.toString(A.class.getNestMembers()));          // Alphabet, Alphabet$A, Alphabet$B, Alphabet$B$B1
        System.out.println(Arrays.toString(B.class.getNestMembers()));          // Alphabet, Alphabet$A, Alphabet$B, Alphabet$B$B1
        System.out.println(Arrays.toString(B.B1.class.getNestMembers()));       // Alphabet, Alphabet$A, Alphabet$B, Alphabet$B$B1

        System.out.println("---");

        System.out.println(Alphabet.class.isNestmateOf(Alphabet.class));        // true
        System.out.println(Alphabet.class.isNestmateOf(A.class));               // true
        System.out.println(Alphabet.class.isNestmateOf(B.class));               // true
        System.out.println(Alphabet.class.isNestmateOf(B.B1.class));            // true

        System.out.println("---");

        System.out.println(A.class.isNestmateOf(Alphabet.class));               // true
        System.out.println(A.class.isNestmateOf(A.class));                      // true
        System.out.println(A.class.isNestmateOf(B.class));                      // true
        System.out.println(A.class.isNestmateOf(B.B1.class));                   // true

        System.out.println("---");

        System.out.println(B.class.isNestmateOf(Alphabet.class));               // true
        System.out.println(B.class.isNestmateOf(A.class));                      // true
        System.out.println(B.class.isNestmateOf(B.class));                      // true
        System.out.println(B.class.isNestmateOf(B.B1.class));                   // true

        System.out.println("---");

        System.out.println(B.B1.class.isNestmateOf(Alphabet.class));            // true
        System.out.println(B.B1.class.isNestmateOf(A.class));                   // true
        System.out.println(B.B1.class.isNestmateOf(B.class));                   // true
        System.out.println(B.B1.class.isNestmateOf(B.B1.class));                // true

    }
}
