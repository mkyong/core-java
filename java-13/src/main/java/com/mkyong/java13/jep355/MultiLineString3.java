package com.mkyong.java13.jep355;

public class MultiLineString3 {

    public static void main(String[] args) {

        String html = String.format("%s\n%s\n%s\n%s\n%s"
                , "<html>"
                , "     <body>"
                , "         <p>Hello, World</p>"
                , "     </body>"
                , "</html>");

        String json = String.format("%s\n%s\n%s\n%s"
                , "{"
                , "     \"name\":\"mkyong\","
                , "     \"age\":38"
                , "}");

        System.out.println(html);
        System.out.println(json);

    }
}
