package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class JsonExample {
    public static void main(String[] args) {
        Contact[] contacts = {
                new Contact("01", "пожарная"),
                new Contact("03", "скорая помощь"),
                new Contact("02", "милиция")
        };
        final Smartphone smartphone1 = new Smartphone(
                false,
                2048,
                "Nokia",
                new Display(),
                contacts
        );

        final Gson gson = new GsonBuilder().create();
        String jsonStr = gson.toJson(smartphone1);
        System.out.println(jsonStr);
        final Smartphone smartphone2 = gson.fromJson(jsonStr, Smartphone.class);
        System.out.println(smartphone2);
    }
}
