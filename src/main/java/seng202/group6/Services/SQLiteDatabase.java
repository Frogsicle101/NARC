package seng202.group6.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import seng202.group6.Models.Crime;

public class SQLiteDatabase {
    private static final String jdbcUrl = "jdbc:sqlite:crimes.db";  //Constant that stores path to database file
    private static Connection connection;   //Stores database connection. Initialised by connectToDatabase()

    public static void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl);  //Gets connection to database

        System.out.println("Connected to Database");
    }

    public static void createTable() throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS crimes (" +
                "case_id CHAR(8) PRIMARY KEY," +
                //"occurrence_date DATETIME NOT NULL, " +
                "block VARCHAR(50) NOT NULL," +
                "iucr CHAR(4) NOT NULL," +
                "primary_description VARCHAR(50) NOT NULL," +
                "secondary_description VARCHAR(50) NOT NULL," +
                "location VARCHAR(50)," +
                "arrest BOOL NOT NULL," +
                "domestic  BOOL NOT NULL," +
                "beat INT NOT NULL," +
                "ward INT NOT NULL," +
                "fbi_cd VARCHAR(3) NOT NULL," +
                "latitude DECIMAL," +
                "longitude DECIMAL" +
                ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public static void insertIntoTable(Crime crime) throws SQLException {
        String sqlInsert ="INSERT INTO crimes VALUES (";

        String sql = sqlInsert + "'" + crime.getCaseNumber() + "', " +
                //str.append(crime.getDate() + ", ");   //Dates don't seem to work yet
                "'" + crime.getBlock() + "', " +
                "'" + crime.getIUCR() + "', " +
                "'" + crime.getPrimaryDescription() + "', " +
                "'" + crime.getSecondaryDescription() + "', " +
                "'" + crime.getLocationDescription() + "', " +
                crime.isArrest() + ", " +
                crime.isDomestic() + ", " +
                crime.getBeat() + ", " +
                crime.getWard() + ", " +
                "'" + crime.getFBI() + "', " +
                crime.getLatitude() + ", " +
                crime.getLongitude() + ")";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
