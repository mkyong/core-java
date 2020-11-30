package com.mkyong.io.howto;

import org.apache.commons.io.FilenameUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GetFileExtension {

    private static final String OUTPUT_FORMAT = "Path: %-30s -> File Extension: %s";
    private static final String WINDOWS_FILE_SEPARATOR = "\\";
    private static final String UNIX_FILE_SEPARATOR = "/";
    private static final String FILE_EXTENSION = ".";
    private static final Map<String, String> KNOWN_EXTENSION = createKnownExtensionMap();

    public static void main(String[] args) {

        String[] files = {
                "/path/foo.txt",
                ".",
                "..",
                "/path/run.exe",
                "/path/makefile",
                "/path/.htaccess",
                "/path/.tar.gz",
                "/path/../makefile",
                "/path/dir.test/makefile"
        };

        for (String file : files) {
            String output = String.format(OUTPUT_FORMAT, file, getFileExtensionImproved(file));
            System.out.println(output);
        }

    }

    public static String getFileExtensionApacheCommon(final String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null!");
        }

        return FilenameUtils.getExtension(fileName);
    }

    /**
     * Fail for below cases
     * <p>
     * "/path/../makefile",
     * "/path/dir.test/makefile"
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null!");
        }

        String extension = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }

        return extension;

    }

    /**
     * Add extra checking for below cases
     * <p>
     * "/path/../makefile",
     * "/path/dir.test/makefile"
     */
    public static String getFileExtensionImproved(String fileName) {

        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null!");
        }

        String extension = "";

        int indexOfLastExtension = fileName.lastIndexOf(FILE_EXTENSION);

        // check last file separator, windows and unix
        int lastSeparatorPosWindows = fileName.lastIndexOf(WINDOWS_FILE_SEPARATOR);
        int lastSeparatorPosUnix = fileName.lastIndexOf(UNIX_FILE_SEPARATOR);

        // takes the greater of the two values, which mean last file separator
        int indexOflastSeparator = Math.max(lastSeparatorPosWindows, lastSeparatorPosUnix);

        // make sure the file extension appear after the last file separator
        if (indexOfLastExtension > indexOflastSeparator) {
            extension = fileName.substring(indexOfLastExtension + 1);
        }

        return extension;

    }

    public static String getFileExtensionKnownExtension(final String fileName) {

        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null!");
        }

        // if the file name is end with the hard coded extension.
        String extension = KNOWN_EXTENSION
                .entrySet()
                .stream()
                .filter(x -> fileName.endsWith(x.getKey()))
                .map(x -> x.getValue())
                .collect(Collectors.joining());

        if ("".equals(extension)) {
            extension = getFileExtensionImproved(fileName);
        }

        return extension;

    }

    private static Map<String, String> createKnownExtensionMap() {
        Map<String, String> result = new HashMap<>();
        result.put(".tar.gz", "tar.gz");    // if .tar.gz, gets tar.gz
        result.put("makefile", "make");     // if makefile, get make
        //...extra
        return result;
    }

}
