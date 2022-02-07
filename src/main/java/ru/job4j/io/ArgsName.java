package ru.job4j.io;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        return Optional.ofNullable(values.get(key)).orElseThrow(() -> new IllegalArgumentException("ключ '" + key + "' не существует"));
    }

    private void parse(String[] args) throws IllegalArgumentException {
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("ключ должен начинаться с '-' (" + arg + ")");
            }
            int index = arg.indexOf("=");
            if (index == -1) {
                throw new IllegalArgumentException("Пропущен знак '=' (" + arg + ")");
            }
            if (index == 1) {
                throw new IllegalArgumentException("Пропущен ключ (" + arg + ")");
            }
            String key = arg.substring(1, index);
            if (index == arg.length() - 1) {
                throw new IllegalArgumentException("Пропущено значение ключа '" + key + "' (" + arg + ")");
            }
            String val = arg.substring(index + 1);
            values.put(key, val);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
