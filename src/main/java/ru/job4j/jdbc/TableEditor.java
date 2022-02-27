package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = properties.getProperty("uri", "jdbc:postgresql://localhost:5432/idea_db");
            String login = properties.getProperty("login", "postgres");
            String password = properties.getProperty("password", "password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws SQLException {
        throwSqlException();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists %s(id serial primary key);", tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        throwSqlException();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "drop table if exists %s;", tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        throwSqlException();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        throwSqlException();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        throwSqlException();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
        }
    }

    public void throwSqlException() throws SQLException {
        if (connection == null) {
            throw new SQLException("SQL Connection is not open");
        }
    }

    public static Properties getJdbcProperties(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(fileName.endsWith(".properties") ? fileName : fileName + ".properties");
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return property;
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public Connection getConnection() {
        return  connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try {
            final String tableName = "testTable";
            TableEditor tableEditor = new TableEditor(TableEditor.getJdbcProperties("app"));
            System.out.println("Create table...");
            tableEditor.createTable(tableName);
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), tableName));
            System.out.println("Create column...");
            tableEditor.addColumn(tableName, "testColumn", "int");
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), tableName));
            System.out.println("Rename column...");
            tableEditor.renameColumn(tableName, "testColumn", "newTestColumn");
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), tableName));
            System.out.println("Delete column...");
            tableEditor.dropColumn(tableName, "newTestColumn");
            System.out.println(TableEditor.getTableScheme(tableEditor.getConnection(), tableName));
            System.out.println("Delete table...");
            tableEditor.dropTable(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
