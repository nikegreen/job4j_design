package ru.job4j.serialization.json;

public class Display {
    private int height;
    private int width;

    public Display() {
        height = 1920;
        width = 1080;
    }

    public Display(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Display{height=" + height + ", width=" + width + '}';
    }
}
