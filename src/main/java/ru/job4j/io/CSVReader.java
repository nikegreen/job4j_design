package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class CSVReader {
/**
 * CSVReader.handle - копирует столбцы из файла типа .csv в другой .csv файл
 * ожидаются аргументы:
 * path - путь и имя .csv файла источника
 * delimiter - разделитель между столбцами
 * out - путь и имя .csv файла с результатом
 * filter - имена столбцов, которые нужно скопировать (перечислены через символ ',')
 */
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<String> filters = List.of(filter.split(","));
        Set<Integer> setFilters = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("UTF-8")));
             PrintWriter pw = new PrintWriter(
                     new FileWriter(out, Charset.forName("UTF-8"), true))) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
