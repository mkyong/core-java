package com.mkyong.java13.jep355;

public class MultiLineString1 {

    public static void main(String[] args) {

        String html = "<html>\n"
                    + "     <body>\n"
                    + "         <p>Hello, World</p>\n"
                    + "     </body>\n"
                    + "</html>";

        String json = "{\n"
                    + "     \"name\":\"mkyong\",\n"
                    + "     \"age\":38\n"
                    + "}";

        System.out.println(html);
        System.out.println(json);

    }
}
