package seng202.group6.ServiceTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;
import seng202.group6.Services.SQLiteDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SQLiteDatabaseTest {
    public static String tableName;

    @BeforeAll
    public static void setUp() {
        String jdbcUrl = "jdbc:sqlite:test.db";
        tableName = "test";
        try {
            SQLiteDatabase.connectToDatabase(jdbcUrl);
        } catch (SQLException e) {}
    }

    @AfterEach
    public void clearDatabase() {
        try {
            SQLiteDatabase.endTransaction();

            String sql = "DELETE FROM " + tableName + ";";    //Clear all rows from table
            Statement statement = SQLiteDatabase.getConnection().createStatement();
            statement.executeUpdate(sql);

            SQLiteDatabase.endTransaction();
        } catch (SQLException e) {}
    }

    @Test
    public void connectToDatabaseTest() {
        assertNotNull(SQLiteDatabase.getConnection());
    }

    @Test
    public void createTableTest() throws SQLException {
        ResultSet tableNames = null;
        try {
            SQLiteDatabase.createTable("test");
            tableNames = SQLiteDatabase.getTableNames();
        } catch (SQLException e) {

        }
        assertEquals("test", tableNames.getString(1));
    }

    @Test
    public void insertIntoTableTest() {
        String sql = "SELECT COUNT(*) FROM " + tableName + ";";   //SQL Query to get the number of rows in a table
        Crime testCrime1 = new Crime("JE266628", LocalDateTime.parse("2021-06-15T09:30"),
                "080XX S DREXEL AVE", "820", "THEFT",
                "$500 AND UNDER", false, false, 631, 8, "6",
                "STREET", 41.748486365, -87.602675062);
        Crime testCrime2 = new Crime("JE266416", LocalDateTime.parse("2021-06-15T12:15"),
                "115XX S YALE AVE", "4387", "OTHER OFFENSE",
                "VIOLATE ORDER OF PROTECTION", false, true, 522, 34, "26",
                "RESIDENCE - PORCH / HALLWAY", 41.684663397, -87.628870501);
        ResultSet result;
        int numCrimes = 0;
        try {
            SQLiteDatabase.insertIntoTable(tableName, testCrime1);
            SQLiteDatabase.insertIntoTable(tableName, testCrime2);
            result = SQLiteDatabase.executeQuery(sql);
            while(result.next()) {
                numCrimes = Integer.parseInt(result.getString(1));
            }
            result.close();
            SQLiteDatabase.endTransaction();
        } catch (SQLException e) {}
        assertEquals(2, numCrimes);
    }
/*
    @Test
    public void insertDuplicateCrimeTest() {
        String sql = "SELECT COUNT(*) FROM " + tableName + ";";   //SQL Query to get the number of rows in a table
        Crime testCrime1 = new Crime("JE266628", LocalDateTime.parse("2021-06-15T09:30"),
                "080XX S DREXEL AVE", "820", "THEFT",
                "$500 AND UNDER", false, false, 631, 8, "6",
                "STREET", 41.748486365, -87.602675062);
        Crime testCrime2 = new Crime("JE266628", LocalDateTime.parse("2021-06-15T09:30"),
                "080XX S DREXEL AVE", "820", "THEFT",
                "$500 AND UNDER", false, false, 631, 8, "6",
                "STREET", 41.748486365, -87.602675062);
        ResultSet result;
        int numCrimes = 0;
        try {
            SQLiteDatabase.insertIntoTable(tableName, testCrime1);
            SQLiteDatabase.insertIntoTable(tableName, testCrime2);
            result = SQLiteDatabase.executeQuery(sql);
            while(result.next()) {
                numCrimes = Integer.parseInt(result.getString(1));
            }
            result.close();
        } catch (SQLException e) {}
        assertEquals(1, numCrimes);
    }
*/
}
