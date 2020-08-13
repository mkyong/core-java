package com.mkyong.io.howto.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class TarGzipExample {

    public static void main(String[] args) {

        try {

            /*Path path1 = Paths.get("/home/mkyong/test/sitemap.xml");
            Path path2 = Paths.get("/home/mkyong/test/file.txt");
            Path output = Paths.get("/home/mkyong/test/output.tar.gz");

            List<Path> paths = Arrays.asList(path1, path2);
            createTarGzipFiles(paths, output);*/

            // tar.gz a folder
            //Path source = Paths.get("/usr/lib/jvm/java-8-openjdk-amd64/");
            Path source = Paths.get("/home/mkyong/test");
            createTarGzipFolder(source);

            //createTarGzipFilesOnDemand();

            // decompress .tar.gz
            /*Path source = Paths.get("/home/mkyong/test/output.tar.gz");
            Path target = Paths.get("/home/mkyong/test2");
            decompressTarGzipFile(source, target);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

    // tar.gz few files
    public static void createTarGzipFiles(List<Path> paths, Path output) throws IOException {

        try (OutputStream fOut = Files.newOutputStream(output);
             BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
             TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {

            for (Path path : paths) {

                if (!Files.isRegularFile(path)) {
                    throw new IOException("Support only file!");
                }

                TarArchiveEntry tarEntry = new TarArchiveEntry(path.toFile(), path.getFileName().toString());
                tOut.putArchiveEntry(tarEntry);

                // copy file to TarArchiveOutputStream
                Files.copy(path, tOut);

                tOut.closeArchiveEntry();

            }

            tOut.finish();

        }

    }

    // generate .tar.gz file at the current working directory
    // tar.gz a folder
    public static void createTarGzipFolder(Path source) throws IOException {

        if (!Files.isDirectory(source)) {
            throw new IOException("Please provide a directory.");
        }

        // get folder name as zip file name
        String tarFileName = source.getFileName().toString() + ".tar.gz";

        try (OutputStream fOut = Files.newOutputStream(Paths.get(tarFileName));
             BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
             TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {

            Files.walkFileTree(source, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

                    // only copy files, no symbolic links
                    if (attributes.isSymbolicLink()) {
                        return FileVisitResult.CONTINUE;
                    }

                    // get filename
                    Path targetFile = source.relativize(file);

                    try {
                        TarArchiveEntry tarEntry = new TarArchiveEntry(
                                file.toFile(), targetFile.toString());

                        tOut.putArchiveEntry(tarEntry);

                        Files.copy(file, tOut);

                        tOut.closeArchiveEntry();

                        System.out.printf("file : %s%n", file);

                    } catch (IOException e) {
                        System.err.printf("Unable to tar.gz : %s%n%s%n", file, e);
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.printf("Unable to tar.gz : %s%n%s%n", file, exc);
                    return FileVisitResult.CONTINUE;
                }

            });

            tOut.finish();
        }

    }

    // Create two files without save to disk and add to tar.gz
    public static void createTarGzipFilesOnDemand() throws IOException {

        String data1 = "Test data 1";
        String fileName1 = "111.txt";

        String data2 = "Test data 2 3 4";
        String fileName2 = "folder/222.txt";

        String outputTarGzip = "/home/mkyong/output.tar.gz";

        try (OutputStream fOut = Files.newOutputStream(Paths.get(outputTarGzip));
             BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
             TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {

            createTarArchiveEntry(fileName1, data1, tOut);
            createTarArchiveEntry(fileName2, data2, tOut);

            tOut.finish();
        }

    }

    private static void createTarArchiveEntry(String fileName,
                                              String data,
                                              TarArchiveOutputStream tOut)
            throws IOException {

        byte[] dataInBytes = data.getBytes();

        // create a byte[] input stream
        ByteArrayInputStream baOut1 = new ByteArrayInputStream(dataInBytes);

        TarArchiveEntry tarEntry = new TarArchiveEntry(fileName);

        // defined a file size, can't exceeds this size
        // tarEntry.setSize(baOut1.available()); alternative
        tarEntry.setSize(dataInBytes.length);

        tOut.putArchiveEntry(tarEntry);

        // copy ByteArrayInputStream to TarArchiveOutputStream
        byte[] buffer = new byte[1024];
        int len;
        while ((len = baOut1.read(buffer)) > 0) {
            tOut.write(buffer, 0, len);
        }

        tOut.closeArchiveEntry();

    }

    public static void decompressTarGzipFile(Path source, Path target) throws IOException {

        if (Files.notExists(source)) {
            throw new IOException("File doesn't exists!");
        }

        try (InputStream fi = Files.newInputStream(source);
             BufferedInputStream bi = new BufferedInputStream(fi);
             GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
             TarArchiveInputStream ti = new TarArchiveInputStream(gzi)) {

            ArchiveEntry entry;
            while ((entry = ti.getNextEntry()) != null) {

                // create a new path, zip slip validate
                Path newPath = zipSlipProtect(entry, target);

                if (entry.isDirectory()) {
                    Files.createDirectories(newPath);
                } else {

                    // check parent folder again
                    Path parent = newPath.getParent();
                    if (parent != null) {
                        if (Files.notExists(parent)) {
                            Files.createDirectories(parent);
                        }
                    }

                    // copy TarArchiveInputStream to Path newPath
                    Files.copy(ti, newPath, StandardCopyOption.REPLACE_EXISTING);

                }
            }
        }
    }

    private static Path zipSlipProtect(ArchiveEntry entry, Path targetDir) throws IOException {

        Path targetDirResolved = targetDir.resolve(entry.getName());

        // make sure normalized file still has targetDir as its prefix, else throws exception
        Path normalizePath = targetDirResolved.normalize();

        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad entry: " + entry.getName());
        }

        return normalizePath;
    }

}
