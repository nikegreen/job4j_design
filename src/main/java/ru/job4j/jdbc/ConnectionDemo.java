package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String path = "app.properties";
        String driver;
        String url;
        String login;
        String password;
        try {
            Config config = new Config(path);
            config.load();
            driver = config.value(("driver"));
            Class.forName(driver);
            url = config.value("url");
            login = config.value("login");
            password = config.value("password");
            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
