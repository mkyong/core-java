package com.mkyong.java18.jep421;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JEP421 {

    public static void main(String[] args) throws IOException {

        String file1 = "/Home/mkyong/file1";
        String file2 = "/Home/mkyong/file1";

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            input = new FileInputStream(file1);
            output = new FileOutputStream(file2);

            // copy files from file1 to file 2

            output.close();
            output = null;
            input.close();
            input = null;
        } finally {
            if (output != null) output.close();
            if (input != null) input.close();
        }

        /*try (FileInputStream input = new FileInputStream(file1);
             FileOutputStream output = new FileOutputStream(file2)) {
            //
        }*/


    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
