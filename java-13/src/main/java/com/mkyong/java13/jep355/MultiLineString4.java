package com.mkyong.java13.jep355;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MultiLineString4 {

    public static void main(String[] args) {

        // no need close this
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("<html>");
        pw.println("     <body>");
        pw.println("         <p>Hello, World</p>");
        pw.println("     </body>");
        pw.println("</html>");
        String html = sw.toString();

        StringWriter sw2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter(sw2);
        pw2.println("{");
        pw2.println("    \"name\":\"mkyong\",");
        pw2.println("    \"age\":38");
        pw2.println("}");
        String json = sw2.toString();

        System.out.println(html);
        System.out.println(json);

    }
}
