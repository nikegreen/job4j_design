package ru.job4j.io.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.matches;

public class Find {
    private static Path path;
    private static String mask;
    private static int type;
    private static String out;
    private static Predicate<Path> condition;
    private static Pattern pattern;

    private static void validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        String pathStr = argsName.get("d");
        path = Paths.get(pathStr);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Не каталог (" + pathStr + ")");
        }
        mask = argsName.get("n");
        String typeStr = argsName.get("t");
        PathMatcher matcher;
        switch (typeStr) {
            case "name":
                condition = f -> mask.equals(f.getFileName().toString());
                break;
            case "mask":
                pattern = Pattern.compile(
                        mask.replace(".", "\\.")
                            .replace("*", ".*"));
                condition = f -> pattern.matcher(f.toString()).find();
                break;
            case "regex":
                pattern = Pattern.compile(mask);
                condition = f -> pattern.matcher(f.toString()).find();
                break;
            default:
                throw new IllegalArgumentException("недопустимый тип ключа -t (" + typeStr + ")");
        }
        out = argsName.get("o");
    }

    private static void find(String[] args) throws IOException {
        validate(args);
        List<Path> list = Search.search(path, condition);
        try (PrintWriter pw = new PrintWriter(new FileWriter(out))) {
            list.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        find(args);
    }
}
