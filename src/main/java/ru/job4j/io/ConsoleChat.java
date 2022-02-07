package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<String> log = new ArrayList<>();
        String say;
        List<String> answers = readPhrases();
        boolean isStop = false;
        System.out.println("--------- chat bot -----------");
        System.out.println("'" + OUT + "' - заканчивает чат");
        System.out.println("'" + STOP + "' - остановить ответы");
        System.out.println("'" + CONTINUE + "' - продолжить ответы");
        System.out.println("------------------------------");
        System.out.print("you>");
        say = scanner.nextLine();
        log.add("you>" + say);
        while (!OUT.equals(say)) {
            if (STOP.equals(say)) {
                isStop = true;
            }
            if (CONTINUE.equals(say)) {
                isStop = false;
            }
            if (!isStop) {
                int index = random.nextInt(answers.size());
                String answer = "bot>" + answers.get(index);
                System.out.println(answer);
                log.add(answer);
            }
            System.out.print("you>");
            say = scanner.nextLine();
            log.add("you>" + say);
        }
        System.out.print("--------- закончить ----------");
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat.log", "./data/answers.txt");
        cc.run();
    }
}
