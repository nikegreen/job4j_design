package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path sourcePath : sources) {
                zip.putNextEntry(new ZipEntry(sourcePath.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sourcePath.toFile()))) {
                        zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String source = argsName.get("d");
        Path sourcePath = Paths.get(source);
        if (!Files.isDirectory(sourcePath)) {
            throw new  IllegalArgumentException("Не директория");
        }
        String exclude = "." + argsName.get("e");
        String dest = argsName.get("o");
        Path destPath = Paths.get(dest);

        SearchFiles searchFiles = new SearchFiles(f -> !f.endsWith(exclude));
        Files.walkFileTree(sourcePath, searchFiles);
        packFiles(searchFiles.getPaths(), destPath);
        searchFiles.getPaths().forEach(System.out::println);
    }
}
