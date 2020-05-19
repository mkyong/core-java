package com.mkyong.java13.jep355;

public class TextBlockExample1 {

    public static void main(String[] args) {

        String html =   """
                        <html>
                            <body>
                                <p>Hello, World</p>
                            </body>
                        </html>
                        """;

        String json =   """
                        {
                            "name":"mkyong",
                            "age":38
                        }
                        """;

        String html2 =   """
                        <html>
                        \t<body>
                        \t\t<p>Hello, World</p>
                        \t</body>
                        </html>
                        """;

        // Concatenation of text blocks
        String html3 = "<html>\n" +
                        """
                            <body>
                                <p>Hello, World</p>
                            </body>
                        """+
                        "</html>";

        System.out.println(html);
        System.out.println(html2);
        System.out.println(html3);
        System.out.println(json);

    }
}
