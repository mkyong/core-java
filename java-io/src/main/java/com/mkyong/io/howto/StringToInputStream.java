package com.mkyong.io.howto;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StringToInputStream {

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {

        String name = "mkyong.com";

        //String to InputStream
        InputStream is = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));

        // commons-io
        // InputStream is = IOUtils.toInputStream(name, StandardCharsets.UTF_8);

        save(is, "c:\\test\\file.txt");

    }

    // save the InputStream to a File
    private static void save(final InputStream is, final String fileName)
            throws IOException {

        try (FileOutputStream outputStream =
                     new FileOutputStream(new File(fileName), false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }

}
