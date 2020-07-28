package com.mkyong.io.directory;

import java.io.File;

//https://mkyong.com/java/how-to-create-directory-in-java/
//https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
public class DirectoryCreate {

    /*
    Unlike the createDirectory method, an exception is not thrown if the directory could not be created because it already exists
     */
    public static void main(String[] args) {

        // check if file exits
        String dir = "/home/mkyong/test2/test3/test4/";

        //Path path = Paths.get(dir);

        File file = new File(dir);

        if (file.mkdirs()) {
            System.out.println("Directory is created!");
        } else {
            System.out.println("Failed to create directory!");
        }

        /*try {
            // create a single directory, if parent not exists, it prompts NoSuchFileException
            //Files.createDirectory(path);

            //Files.createDirectories(path);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
