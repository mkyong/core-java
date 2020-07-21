package com.mkyong.io.temp;

public class TempFileGetPath {

    public static void main(String[] args) {

        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);

        // Java NIO
        /*try {
            Path temp = Files.createTempFile("log_", ".tmp");

            String absolutePath = temp.toString();
            System.out.println("Temp file : " + absolutePath);

            String separator = FileSystems.getDefault().getSeparator();
            String tempFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(separator));
            System.out.println("Temp file path : " + tempFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // Java IO
        /*try {
            File temp = File.createTempFile("log_", ".tmp");
            System.out.println("Temp file : " + temp.getAbsolutePath());

            String absolutePath = temp.getAbsolutePath();
            String tempFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
            System.out.println("Temp file path : " + tempFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

}
