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

    public boolean isPowerOn() {
        return powerOn;
    }

    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
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
