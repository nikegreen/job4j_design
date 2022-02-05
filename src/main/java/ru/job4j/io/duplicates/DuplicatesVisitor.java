package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        FileProperty fileProperty = new FileProperty(Files.size(file), fileName);
        if (!files.containsKey(fileProperty)) {
            files.put(fileProperty, new ArrayList<>(1));
        }
        files.get(fileProperty).add(file.toString());
        return super.visitFile(file, attrs);
    }

    public List<String> getDuplicates() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<FileProperty, List<String>> entry:files.entrySet()) {
            if (entry.getValue().size() > 1) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }
}