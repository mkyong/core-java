package com.mkyong.java8.misc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ForEachWriteFile {

    public static void main(String[] args) {

        ForEachWriteFile obj = new ForEachWriteFile();
        obj.save(Paths.get("C:\\test"), obj.createDummyFiles());

        //Path path = Paths.get("C:\\test");
        //obj.createDummyFiles().forEach(o -> obj.saveFile(path, o));
    }

    public void save(Path path, List<DummyFile> files) {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory");
        }

        // extract to a new method
        /*files.forEach(f -> {
            try {
                int id = f.getId();
                // create a filename
                String fileName = id + ".txt";
                Files.write(path.resolve(fileName),
                        f.getContent().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

        files.forEach(f -> saveFile(path, f));

    }

    public void saveFile(Path path, DummyFile f) {
        try {
            int id = f.getId();
            // create a filename
            String fileName = id + ".txt";
            Files.write(path.resolve(fileName),
                    f.getContent().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DummyFile> createDummyFiles() {
        return Arrays.asList(
                new DummyFile(1, "hello"),
                new DummyFile(2, "world"),
                new DummyFile(3, "java"));
    }

    class DummyFile {
        int id;
        String content;

        public DummyFile(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }
}
