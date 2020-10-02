package com.mkyong.io.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// write 5 millions line to a file, 1053 chars per line, file size around 5G
public class FileWriteLargeFile {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/test/large-file.txt";

        // 1053 chars per line
        String content = "Hello 123456 ";
        content = content + content + content;
        content = content + content + content;
        content = content + content + content;
        content = content + content + content;

        System.out.println(content.length());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (int i = 0; i < 5_000_000; i++) {
                bw.write(content);
                bw.newLine();
                //bw.write(System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
