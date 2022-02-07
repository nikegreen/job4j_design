package ru.job4j.serialization.json;

import java.util.Arrays;

public class Smartphone {
    private boolean powerOn;
    private int memory;
    private String model;
    private Display display;
    private Contact[] contacts;

    public Smartphone(boolean powerOn,
                      int memory,
                      String model,
                      Display display,
                      Contact[] contacts) {
        this.powerOn = powerOn;
        this.memory = memory;
        this.model = model;
        this.display = display;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Smartphone{"
              + "powerOn=" + powerOn
              + ", memory=" + memory
              + ", model='" + model + '\''
              + ", display=" + display
              + ", contacts=" + Arrays.toString(contacts)
              + '}';
    }
}
