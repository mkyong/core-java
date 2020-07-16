package com.mkyong.io.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public static void main(String[] args) {

        int buffSize = 1024;
        String fileName = "/home/mkyong/large.txt";

        try (BufferedWriter write = new BufferedWriter(new FileWriter(fileName), buffSize)) {

            for (int i = 0; i < 100_000_000; i++) {
                write.write("Hello World Hello World Hello World\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
