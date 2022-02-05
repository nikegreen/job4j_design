package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args.length > 0 ? args[0] : "");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s",
                    file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    file.getAbsoluteFile()));
        }
        if (args.length < 2) {
            throw new IllegalArgumentException("Not exist extension of file");
        }
        String extension = args[1];
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            if (subfile.getName().endsWith(extension)) {
                System.out.println((subfile.isFile() ? subfile.length() : "<dir>")
                        + " : " + subfile.getName());
            }
        }
    }
}
