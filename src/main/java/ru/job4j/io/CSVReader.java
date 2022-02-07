package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void validate(ArgsName argsName) throws IllegalArgumentException {
        String path = argsName.get("path");
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("исходного файла не существует (" + path + ")");
        }
        if (Files.isDirectory(Paths.get(path))) {
            throw new IllegalArgumentException("нужно имя исходного файла, а не каталог (" + path + ")");
        }
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<String> filters = List.of(filter.split(","));
        if (filter.length() == 0 || filters.size() == 0) {
            throw new IllegalArgumentException("не указаны столбцы");
        }
        for (String s : filters) {
            if (s.length() == 0) {
                throw new IllegalArgumentException("пропущено название столбца");
            }
        }
    }
/**
 * CSVReader.handle - копирует столбцы из файла типа .csv в другой .csv файл
 * ожидаются аргументы:
 * path - путь и имя .csv файла источника
 * delimiter - разделитель между столбцами
 * out - путь и имя .csv файла с результатом
 * filter - имена столбцов, которые нужно скопировать (перечислены через символ ',')
 */
    public static void handle(ArgsName argsName) {
        try {
            validate(argsName);
            String path = argsName.get("path");
            String delimiter = argsName.get("delimiter");
            String out = argsName.get("out");
            String filter = argsName.get("filter");
            List<String> filters = List.of(filter.split(","));
            Set<Integer> setFilters = new HashSet<>();
            BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("UTF-8")));
            PrintWriter pw = "stdout".equals(out)
                    ? new PrintWriter(System.out)
                    : new PrintWriter(new FileWriter(out, Charset.forName("UTF-8"), true));
            Scanner scan1 = new Scanner(br);
            if (scan1.hasNextLine()) {
                Scanner scan2 = new Scanner(scan1.nextLine()).useDelimiter(delimiter);
                int index = 0;
                boolean notFirst = false;
                while (scan2.hasNext()) {
                    String colon = scan2.next();
                    if (filters.contains(colon)) {
                        if (notFirst) {
                            pw.print(delimiter);
                        }
                        notFirst = true;
                        setFilters.add(index);
                        pw.print(colon);
                    }
                    index++;
                }
                pw.println();
            }
            while (scan1.hasNextLine()) {
                Scanner scan2 = new Scanner(scan1.nextLine()).useDelimiter(delimiter);
                int index = 0;
                boolean notFirst = false;
                while (scan2.hasNext()) {
                    String colon = scan2.next();
                    if (setFilters.contains(index)) {
                        if (notFirst) {
                            pw.print(delimiter);
                        }
                        notFirst = true;
                        pw.print(colon);
                    }
                    index++;
                }
                pw.println();
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
