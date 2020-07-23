package com.mkyong.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;
import java.util.Set;

public class FilePermission {

    public static void main(String[] args) {

        try {

            Path path = Paths.get("C:\\test\\test4.log");
            //Path path = Paths.get("/home/mkyong/test/test4.log");

            Set<PosixFilePermission> perms = EnumSet.of(
                    PosixFilePermission.OWNER_READ,
                    PosixFilePermission.OWNER_WRITE,
                    PosixFilePermission.OWNER_EXECUTE,
                    PosixFilePermission.GROUP_READ,
                    PosixFilePermission.GROUP_WRITE,
                    PosixFilePermission.GROUP_EXECUTE,
                    PosixFilePermission.OTHERS_READ,
                    PosixFilePermission.OTHERS_WRITE,
                    PosixFilePermission.OTHERS_EXECUTE
            );

            /*Set<PosixFilePermission> perms =
                    PosixFilePermissions.fromString("rwxrwxrwx");*/
            Files.createFile(path);

            /*
            PosixFilePermissions On Windows
            Exception in thread "main" java.lang.UnsupportedOperationException
	            at java.base/java.nio.file.Files.setPosixFilePermissions(Files.java:2155)
	            at com.mkyong.io.file.FilePermission.main(FilePermission.java:37)
             */
            Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rwxrwxrwx"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
