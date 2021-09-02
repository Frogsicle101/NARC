package seng202.group6.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabase {

    public static void connectToDatabase() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:crimes.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        //Database schema needs to be updated significantly
        String sql = "CREATE TABLE Crimes (CaseID CHAR(8) PRIMARY KEY, Date DATE)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        System.out.println("Table created");
    }
}
