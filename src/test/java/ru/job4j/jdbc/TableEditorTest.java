package ru.job4j.jdbc;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class TableEditorTest extends TestCase {
    final String exp3 = "------------------------------\n"
            + "NAME           |TYPE           \n"
            + "------------------------------\n"
            + "id             |serial         \n"
            + "------------------------------\n"
            + "newtestcolumn  |int4           \n"
            + "------------------------------\n";
    final String exp2 = "------------------------------\n"
            + "NAME           |TYPE           \n"
            + "------------------------------\n"
            + "id             |serial         \n"
            + "------------------------------\n"
            + "testcolumn     |int4           \n"
            + "------------------------------\n";
    final String exp1 = "------------------------------\n"
            + "NAME           |TYPE           \n"
            + "------------------------------\n"
            + "id             |serial         \n"
            + "------------------------------\n";

    @Test
    public void testCreateTable() {
        final String exp = "";
        final String tableName = "testTable";
        TableEditor tableEditor = null;
        try {

            tableEditor = new TableEditor(TableEditor.getJdbcProperties("app"));
            tableEditor.createTable(tableName);
            String res = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            tableEditor.dropTable(tableName);
            assertEquals(exp1, res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = SQLException.class)
    public void testDropTable() {
        final String tableName = "testTable";
        TableEditor tableEditor = null;
        try {
            tableEditor = new TableEditor(TableEditor.getJdbcProperties("app"));
            tableEditor.createTable(tableName);
            String res = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            assertEquals(exp1, res);
            tableEditor.dropTable(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddColumn() {
        final String tableName = "testTable";
        TableEditor tableEditor = null;
        try {
            tableEditor = new TableEditor(TableEditor.getJdbcProperties("app"));
            tableEditor.createTable("testTable");
            tableEditor.addColumn("testTable", "testColumn", "int");
            String res = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            tableEditor.dropTable("testTable");
            assertEquals(exp2, res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDropColumn() {
        final String tableName = "testTable";
        TableEditor tableEditor = null;
        try {
            tableEditor = new TableEditor(TableEditor.getJdbcProperties("app"));
            tableEditor.createTable("testTable");
            String res1 = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            assertEquals(exp1, res1);
            tableEditor.addColumn("testTable", "testColumn", "int");
            String res2 = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            assertEquals(exp2, res2);
            tableEditor.dropColumn("testTable", "testColumn");
            String res3 = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            tableEditor.dropTable("testTable");
            assertEquals(exp1, res3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRenameColumn() {
        final String exp = "";
        final String tableName = "testTable";
        TableEditor tableEditor = null;
        try {
            tableEditor = new TableEditor(TableEditor.getJdbcProperties("app"));
            tableEditor.createTable(tableName);
            String res1 = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            assertEquals(exp1, res1);
            tableEditor.addColumn(tableName, "testColumn", "int");
            String res2 = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            assertEquals(exp2, res2);
            tableEditor.renameColumn(tableName, "testColumn", "newTestColumn");
            String res3 = TableEditor.getTableScheme(tableEditor.getConnection(), tableName);
            assertEquals(exp3, res3);
            tableEditor.dropColumn(tableName, "newTestColumn");
            tableEditor.dropTable(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}