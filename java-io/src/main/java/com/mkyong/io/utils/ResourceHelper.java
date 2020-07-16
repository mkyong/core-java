package com.mkyong.io.utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceHelper {

    public static String getAbsoluteFilePath(String filename) {
        URL resource = ClassLoader.getSystemClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("File: " + filename + " not found.");
        }

        try {
            Path path = Paths.get(resource.toURI());
            return path.toString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
