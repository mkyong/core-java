package com.mkyong.xml.sax;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyAndAddBomToXmlFile {

    public static void main(String[] args) {
        Path src = Paths.get("src/main/resources/staff.xml");
        Path dest = Paths.get("src/main/resources/staff-bom.xml");
        writeBomFile(src, dest);
    }

    private static void writeBomFile(Path src, Path dest) {

        try (FileOutputStream fos = new FileOutputStream(dest.toFile())) {

            byte[] BOM = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            fos.write(BOM);

            Files.copy(src, fos);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
