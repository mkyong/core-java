package com.mkyong.io.howto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class UpdateLastModifiedTime {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws IOException, ParseException {

        String fileName = "c:\\test\\google.png";
        updateLastModifiedDateNio(fileName);

        //updateLastModifiedDateLegacy(fileName);

    }

    private static void updateLastModifiedDateNio(String fileName) throws IOException {

        Path file = Paths.get(fileName);
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        FileTime lastModifiedTime = attr.lastModifiedTime();

        // print original last modified time
        System.out.println("[original] lastModifiedTime:" + lastModifiedTime);

        // year, month, dayOfMonth, hour, minute, second
        LocalDateTime newLocalDateTime = LocalDateTime.of(1999, 9, 30, 10, 30, 22);
        Instant instant = newLocalDateTime.toInstant(ZoneOffset.UTC);

        // convert LocalDate to instant, need time zone
        // LocalDate newLocalDate = LocalDate.of(1998, 9, 30);
        // Instant instant = newLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        // convert instant to filetime
        // update last modified time
        Files.setLastModifiedTime(file, FileTime.from(instant));

        // read last modified time again
        FileTime newLastModifiedTime = Files.readAttributes(file,
                BasicFileAttributes.class).lastModifiedTime();
        System.out.println("[updated] lastModifiedTime:" + newLastModifiedTime);

    }

    private static void updateLastModifiedDateLegacy(String fileName) throws ParseException {

        File file = new File(fileName);

        // print original last modified time
        System.out.println("[original] lastModifiedTime:" + sdf.format(file.lastModified()));

        //need convert the above date to milliseconds in long value
        Date newLastModified = sdf.parse("31/08/1998");

        // update last modified date
        file.setLastModified(newLastModified.getTime());

        //print the latest last modified date
        System.out.println("[updated] lastModifiedTime:" + sdf.format(file.lastModified()));

    }

}