package com.mkyong.system;

public class OSValidator {

    private static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);
    public static boolean IS_MAC = (OS.indexOf("mac") >= 0);
    public static boolean IS_UNIX = (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    public static boolean IS_SOLARIS = (OS.indexOf("sunos") >= 0);

    public static void main(String[] args) {

        System.out.println("os.name: " + OS);

        if (IS_WINDOWS) {
            System.out.println("This is Windows");
        } else if (IS_MAC) {
            System.out.println("This is Mac");
        } else if (IS_UNIX) {
            System.out.println("This is Unix or Linux");
        } else if (IS_SOLARIS) {
            System.out.println("This is Solaris");
        } else {
            System.out.println("Your OS is not support!!");
        }
    }

}