package com.mkyong.java16.jep394;

public class JEP394 {

    public static void main(String[] args) {

        JEP394 obj = new JEP394();
        obj.validateInput("java16");

    }

    private boolean validateInput(Object obj) {

        boolean result = false;

        // pattern matching
        if (obj instanceof String s && s.length() > 5) {
            if (s.equalsIgnoreCase("java16")) {
                result = true;
            }
        }

        // old time
        if (obj instanceof String) {
            String s = (String) obj;
            if (s.length() > 5) {
                if (s.equalsIgnoreCase("java16")) {
                    result = true;
                }
            }
        }

        return result;
    }

}
