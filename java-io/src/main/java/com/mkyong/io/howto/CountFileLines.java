package com.mkyong.io.howto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

// 5 million lines, 1053 chars per line
public class CountFileLines {

    public static void main(String[] args) {

        String fileName = "/home/mkyong/large-file.txt";

        System.out.println(LocalDateTime.now());

        long lines = countLineFast(fileName);

        System.out.println(LocalDateTime.now());

        System.out.println("Total lines: " + lines);
    }

    // 4-5 seconds, faster a bit
    public static long countLineFast(String fileName) {

        long lines = 0;

        try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            lines = count;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    // 6-8 seconds
    public static long countLineBufferedReader(String fileName) {

        long lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

    // 6-8 seconds
    public static long countLineJava8(String fileName) {

        Path path = Paths.get(fileName);

        long lines = 0;
        try {
            // much slower
            //lines = Files.lines(path).parallel().count();

            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }

    // 6-8 seconds
    public static long countLineNumberReader(String fileName) {

        File file = new File(fileName);

        long lines = 0;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

            while (lnr.readLine() != null) ;

            lines = lnr.getLineNumber();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }

    public static void writeLargeFile(String fileName) {

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
                bw.write(System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
