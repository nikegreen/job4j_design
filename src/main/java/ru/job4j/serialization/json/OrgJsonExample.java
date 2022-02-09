package ru.job4j.serialization.json;

import org.json.JSONObject;

public class OrgJsonExample {
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

        final JSONObject jsonObject = new JSONObject(smartphone1);
        String jsonStr = jsonObject.toString();
        System.out.println(jsonStr);
    }
}
