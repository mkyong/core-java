package com.mkyong.java13.jep355;

public class MultiLineString2 {

    public static void main(String[] args) {

        String html = new StringBuilder()
                .append("<html>\n")
                .append("     <body>\n")
                .append("         <p>Hello, World</p>\n")
                .append("     </body>\n")
                .append("</html>")
                .toString();

        String json = new StringBuilder()
                .append("{\n")
                .append("     \"name\":\"mkyong\",\n")
                .append("     \"age\":38\n")
                .append("}").toString();

        System.out.println(html);
        System.out.println(json);

    }
}
