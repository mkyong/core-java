package com.mkyong.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;
import java.util.Set;

public class FilePermission {

    public static void main(String[] args) {

        try {

            Path path = Paths.get("/home/mkyong/test/test4.log");

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
            Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rwxrwxrwx"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
