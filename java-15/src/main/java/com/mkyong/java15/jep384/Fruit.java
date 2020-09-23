package com.mkyong.java15.jep384;

public sealed interface Fruit permits Apple, Orange {}

record Apple() implements Fruit{}
record Orange() implements Fruit{}
