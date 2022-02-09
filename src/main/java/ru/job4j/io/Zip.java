package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static Path destPath;
    private static String exclude;
    private static Path sourcePath;

    public static void validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        sourcePath = Paths.get(argsName.get("d"));
        if (!Files.isDirectory(sourcePath)) {
            throw new  IllegalArgumentException("Не директория");
        }
        exclude = "." + argsName.get("e");
        if (exclude.charAt(1) == '.') {
            throw new  IllegalArgumentException("Расширение файла не должно начинаться с '.'");
        }
        destPath = Paths.get(argsName.get("o"));
    }

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
        validate(args);
        SearchFiles searchFiles = new SearchFiles(f -> !f.toString().endsWith(exclude));
        Files.walkFileTree(sourcePath, searchFiles);
        packFiles(searchFiles.getPaths(), destPath);
        searchFiles.getPaths().forEach(System.out::println);
    }
}
