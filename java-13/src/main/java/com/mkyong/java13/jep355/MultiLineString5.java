package com.mkyong.java13.jep355;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MultiLineString5 {

    public static void main(String[] args) {

        String html = String.join("\n"
                , "<html>"
                , "     <body>"
                , "         <p>Hello, World</p>"
                , "     </body>"
                , "</html>");

        String json = String.join("\n"
                , "{"
                , "    \"name\":\"mkyong\","
                , "    \"age\":38"
                , "}");

        System.out.println(html);
        System.out.println(json);

    }
}
