package ru.job4j.serialization.xml;

public class InternetBox {

    class Addr {
        private String country;
        private String city;
        private String street;
        private int home;

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

    private boolean delivered;
    private int weight;
    private String postCompany;
    private Addr addr;
    private String[] descriptions;

    public InternetBox(
            boolean delivered,
            int weight,
            String postCompany,
            Addr addr,
            String[] descriptions) {
        this.delivered = delivered;
        this.weight = weight;
        this.postCompany = postCompany;
        this.addr = addr;
        this.descriptions = descriptions;
    }

    public boolean getDelivered() {
        return delivered;
    }

    public int getWeight() {
        return weight;
    }

    public String getPostCompany() {
        return postCompany;
    }

    public Addr getAddr() {
        return addr;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPostCompany(String postCompany) {
        this.postCompany = postCompany;
    }

    public void setAddr(Addr addr) {
        this.addr = addr;
    }

    public void setDescriptions(String[]  descriptions) {
        this.descriptions = descriptions;
    }
}