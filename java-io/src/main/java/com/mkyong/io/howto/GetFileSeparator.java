package com.mkyong.io.howto;

import java.nio.file.FileSystems;

public class GetFileSeparator {

    public static void main(String[] args) {

        // unix / , windows \
        //String separator = System.getProperty("file.separator");

        // java.io
        // String separator = File.separator;

        // java.nio
        String separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);

    }

}
