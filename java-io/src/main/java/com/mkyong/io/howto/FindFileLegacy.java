package com.mkyong.io.howto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFileLegacy {

    private List<File> result = new ArrayList<>();

    public List<File> getResult() {
        return result;
    }

    public static void main(String[] args) {

        FindFileLegacy obj = new FindFileLegacy();

        File dir = new File("C:\\test");

        obj.findFile(dir, "google.png");

        int size = obj.getResult().size();
        if (size > 0) {
            System.out.println("\nFound " + size + " result!\n");
            for (File f : obj.getResult()) {
                System.out.println("Found : " + f);
            }
        } else {
            System.out.println("\nNo result found!");
        }

    }

    // loop recursive
    public void findFile(File file, String search) {

        if (file.isDirectory()) {
            System.out.println("Searching directory ... " + file.getAbsoluteFile());

            for (File f : file.listFiles()) {

                // if no read permission, skip
                if (!f.canRead()) {
                    continue;
                }

                if (f.isDirectory()) {
                    findFile(f, search);
                } else {
                    // compare filename
                    if (search.equalsIgnoreCase(f.getName())) {
                        result.add(f);
                    }

                }
            }

        }

    }

}