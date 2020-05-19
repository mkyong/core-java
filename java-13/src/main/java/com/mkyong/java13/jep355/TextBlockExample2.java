package com.mkyong.java13.jep355;

public class TextBlockExample2 {

    public static void main(String[] args) {

        String json =   """
                        {
                            "name":"%s",
                            "age":%d,
                            "address":"%s"
                        }
                        """;

        System.out.println(json.formatted("mkyong", 38, "abc"));

        System.out.println(String.format(json, "mkyong", 38, "abc"));

        String json2 =   """
                        {
                            "name":"$name",
                            "age":$age,
                            "address":"$address"
                        }
                        """;

        System.out.println(json2
                .replace("$name", "mkyong")
                .replace("$age", "38")
                .replace("$address", "abc"));

    }
}
