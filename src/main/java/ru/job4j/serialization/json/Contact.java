package ru.job4j.serialization.json;

public class Contact {
    private String phoneNumber;
    private String name;

    public Contact() {
        phoneNumber = "+7";
        name = "";
    }

    public Contact(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phoneNumber='" + phoneNumber + "', name='" + name + "'}";
    }
}
