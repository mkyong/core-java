package com.mkyong.io.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class FilePermission {

    public static void main(String[] args) {

        try {

            Path path = Paths.get("c:\\test\\test6.log");
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
            if(!Files.exists(path))
                Files.createFile(path);

            UserPrincipal owner = path.getFileSystem().getUserPrincipalLookupService()
                    .lookupPrincipalByName("mkyong");

            Files.setOwner(path, owner);

            /*
            File is created and throws UnsupportedOperationException
            PosixFilePermissions On Windows
            Exception in thread "main" java.lang.UnsupportedOperationException
	            at java.base/java.nio.file.Files.setPosixFilePermissions(Files.java:2155)
	            at com.mkyong.io.file.FilePermission.main(FilePermission.java:37)
             */
            //Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rwxrwxrwx"));

            //Files.setAttribute(path, "dos:hidden", true);
            //Files.setAttribute(path, "dos:readonly", false);

            /*FileOwnerAttributeView view = Files.getFileAttributeView(path,
                    FileOwnerAttributeView.class);

            System.out.println(view);

            UserPrincipal owner = path.getFileSystem().getUserPrincipalLookupService()
                    .lookupPrincipalByName("mkyong");

            UserPrincipal owner1 = Files.getOwner(path);
            System.out.println(owner1.getName());


            Files.setAttribute(path, "dos:system", true);
            Files.setOwner(path, owner);*/

            /*// lookup "joe"
            UserPrincipal joe = path.getFileSystem().getUserPrincipalLookupService()
                    .lookupPrincipalByName("mkyong");

            // get view
            AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);

            // create ACE to give "joe" read access
            AclEntry entry = AclEntry.newBuilder()
                    .setType(AclEntryType.ALLOW)
                    .setPrincipal(joe)
                    .setPermissions(AclEntryPermission.READ_DATA, AclEntryPermission.READ_ATTRIBUTES)
                    .build();

            // read ACL, insert ACE, re-write ACL
            List<AclEntry> acl = view.getAcl();
            acl.add(0, entry);   // insert before any DENY entries
            view.setAcl(acl);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
