package seng202.group6.Services;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.maps.model.LatLng;
import seng202.group6.Controllers.MasterController;
import seng202.group6.Models.Crime;

public class SQLiteDatabase {
    private static final String jdbcUrl = "jdbc:sqlite:crimes.db";  //Constant that stores path to database file
    private static Connection connection;   //Stores database connection. Initialised by connectToDatabase()

    /**
     * Creates a connection to the database, and creates it if it doesn't exist
     */
    public static void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl);
        connection.setAutoCommit(false);

        createTable("Crimes");
    }

    /**
     * Checks whether a table exists in the database, and creates it if it doesn't exist
     * @param tableName The name of the table to create
     */
    public static void createTable(String tableName) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS " +tableName+ " (" +
                "case_id CHAR(8) PRIMARY KEY," +
                "occurrence_date VARCHAR(21) NOT NULL, " +
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
    public static void insertIntoTable(String tableName, Crime crime) throws SQLException{
        String sql = "INSERT INTO " + tableName + " VALUES (" + crime.toString() + ")";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

    }

    public static void updateInTable(String tableName, Crime crime) throws SQLException{
        String sql = "UPDATE " + tableName + " " +
                "SET " +
                "occurrence_date = '" + crime.getDate() + "', " +
                "primary_description = '" + crime.getPrimaryDescription() + "', " +
                "location = '" + crime.getLocationDescription() + "', " +
                "secondary_description = '" + crime.getSecondaryDescription() + "', " +
                "arrest = " + crime.isArrest() + ", " +
                "domestic = " + crime.isDomestic() + ", " +
                "beat = " + crime.getBeat() + ", " +
                "ward = " + crime.getWard() + ", " +
                "fbi_cd = '" + crime.getFBI() + "', " +
                "block = '" + crime.getBlock() + "', " +
                "iucr = '" + crime.getIucr() + "', " +
                "latitude = " + crime.getLatitude() + ", " +
                "longitude = " + crime.getLongitude() + " " +
                "WHERE case_id LIKE '" + crime.getCaseNumber() + "';";


        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

    }

    public static void deleteFromTable(String tableName, Crime crime) throws SQLException {
        String sql = "DELETE FROM " + tableName +
                " WHERE case_id = '" + crime.getCaseNumber() + "';";


        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        endTransaction();
    }

    /**
     * Ends an SQL transaction and commits the results to file. Call after every change to the database
     * @throws SQLException
     */
    public static void endTransaction() throws SQLException {
        connection.commit();
    }



    /**
     * Select all data from a table
     * @param tableName The name of the table to select from
     */
    public static ResultSet selectAllFromTable(String tableName) throws SQLException {
        String sql = "SElECT * FROM "+tableName;

        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static ArrayList<Crime> convertResultSet(ResultSet data) throws SQLException {
        ArrayList<Crime> out = new ArrayList<>();

        while (data.next()) {
            Crime newCrime = new Crime(data.getString("case_id"),
                    LocalDateTime.parse(data.getString("occurrence_date")),
                    data.getString("block"),
                    data.getString("iucr"),
                    data.getString("primary_description"),
                    data.getString("secondary_description"),
                    data.getInt("arrest") == 1,
                    data.getInt("domestic") == 1,
                    data.getInt("beat"),
                    data.getInt("ward"),
                    data.getString("fbi_cd"),
                    data.getString("location"),
                    data.getDouble("latitude"),
                    data.getDouble("longitude"));
            out.add(newCrime);
        }
        return out;
    }

    /*public static ResultSet selectLocationsFromTable(LatLng location) throws SQLException {
        Double latRight = location.lat - 0.0036;
        Double latLeft = location.lat + 0.0031;
        Double lngUp = location.lng - 0.0062;
        Double lngDown = location.lng + 0.0062;
        String sql = "SELECT * FROM crimes WHERE (latitude BETWEEN "+latRight+" AND "
                +latLeft+") AND (longitude BETWEEN "+lngUp +" AND "+lngDown+")";
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }*/

    public static ResultSet executeQuery(String query) throws SQLException {

        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
