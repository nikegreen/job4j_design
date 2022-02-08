package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Addr")
@XmlAccessorType(XmlAccessType.FIELD)
public class Addr {
    @XmlAttribute
    private String country;

    @XmlAttribute
    private String city;

    @XmlAttribute
    private String street;

    @XmlAttribute
    private int home;

    public Addr() {

    }

    public Addr(String country, String city, String street, int home) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }
}