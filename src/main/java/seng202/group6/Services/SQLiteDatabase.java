package seng202.group6.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import seng202.group6.Models.Crime;

public class SQLiteDatabase {
    private static final String jdbcUrl = "jdbc:sqlite:crimes.db";  //Constant that stores path to database file
    private static Connection connection;   //Stores database connection. Initialised by connectToDatabase()

    /**
     * Creates a connection to the database, and creates it if it doesn't exist
     */
    public static void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl);

        System.out.println("Connected to Database");
    }

    /**
     * Checks whether a table exists in the database, and creates it if it doesn't exist
     * @param tableName The name of the table to create
     */
    public static void createTable(String tableName) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ " (" +
                "case_id CHAR(8) PRIMARY KEY," +
                //"occurrence_date DATETIME NOT NULL, " +       Commented out until dates work
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

    /**
     * Inserts a crime object into a table
     * @param tableName The name of the table to insert into
     * @param crime The crime object to insert into the table
     */
    public static void insertIntoTable(String tableName, Crime crime) throws SQLException {
        String sqlInsert ="INSERT INTO " + tableName + " VALUES (";

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
