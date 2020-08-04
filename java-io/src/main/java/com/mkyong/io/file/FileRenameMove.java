package com.mkyong.io.file;

import java.io.IOException;
import java.nio.file.*;

public class FileRenameMove {

    public static void main(String[] args) {

        String fromFile = "/home/mkyong/data/db.debug.conf";
        String toFile = "/home/mkyong/data/deploy/db.conf";
        String toDir = "/home/mkyong/data/deploy/";

        Path source = Paths.get(fromFile);
        Path target = Paths.get(toFile);

        try {

            // rename or move a file to other path
            // if target exists, throws FileAlreadyExistsException
            Files.move(source, target);

            // if target exists, replace it.
            // Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

            // rename a file in the same directory
            //Files.move(source, source.resolveSibling("newName.txt"));

            // move file to another directory, using the same file name
            //Path newDir = Paths.get("/home/mkyong/newfolder/");

            // create the directories, if directory exits, no effect
            //Files.createDirectories(newDir);

            //Files.move(source, newDir.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);

            // apache commons-io
            //FileUtils.moveFile(new File("/source"), new File("/target"));


            /*CopyOption[] options = { StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES,
                    LinkOption.NOFOLLOW_LINKS };

            Files.move(source, target, options);*/


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
