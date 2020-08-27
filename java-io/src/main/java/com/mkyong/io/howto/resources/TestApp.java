package com.mkyong.io.howto.resources;

import java.net.URISyntaxException;

public class TestApp {

    public static void main(String[] args) {

        TestApp obj = new TestApp();
        //obj.getFileFromResources(null);

        try {

            String jarPath = TestApp.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            System.out.println("JAR Path : " + jarPath);

            String jarPath2 = TestApp.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath();
            System.out.println("JAR Path 2 : " + jarPath2);

            //String jarName = jarPath.substring(jarPath.lastIndexOf("/") + 1);
            //System.out.printf("JAR Name: " + jarName);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /*private InputStream getFileFromResources(List<String> fileNames) throws URISyntaxException, IOException {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource("json");

        String path = TestApp.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        System.out.println("path 1:" + path);

        System.out.println(resource);

        URI uri = URI.create("jar:file:/home/mkyong/projects/core-java/java-io/target/java-io.jar");

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {

            List<Path> collect = Files.walk(zipfs.getPath("json"), 5)
                    .filter(Files::isRegularFile)
                    //.map(x -> )
                    .collect(Collectors.toList());

            collect.forEach(x -> System.out.println(x));

        }

        //InputStream inputStream = classLoader.getResourceAsStream(fileName);

        return null;

    }

    private String getPathOfRunningJAR() {

        String result = null;
        try {
            result = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return result;

    }*/
}
