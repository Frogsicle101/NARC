package seng202.group6.Services;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


import seng202.group6.Models.Crime;

/**
 * This class contains code for interacting with the database, including creating the database,
 * creating and dropping tables, and inserting, editing, and deleting crime objects from the database.
 *
 * Importantly, for efficiency reasons the database is set to not autocommit changes to the database.
 * Thus, after making any changes to the database endTransaction() should be called to commit those changes.
 */

public class SQLiteDatabase {

    private static final String jdbcUrl = "jdbc:sqlite:crimes.db";  //Constant that stores path to database file
    private static Connection connection;   //Stores database connection. Initialised by connectToDatabase()

    /**
     * Creates a connection to the database, and creates it if it doesn't exist
     * @param jdbcUrl The path to the database file
     */
    public static void connectToDatabase(String jdbcUrl) throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl);
        connection.setAutoCommit(false);

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
        endTransaction();
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

    /**
     * Updates a crime object in a particular table
     * @param tableName The name of the table
     * @param crime The new object
     */
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

    /**
     * Deletes a crime from a particular table
     * More precisely, this method deletes the record that has the same crime_id as the given Crime
     * @param tableName The name of the table to delete from
     * @param crime The crime to delete
     */
    public static void deleteFromTable(String tableName, Crime crime) throws SQLException {
        String sql = "DELETE FROM " + tableName +
                " WHERE case_id = '" + crime.getCaseNumber() + "';";


        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        endTransaction();
    }

    /**
     * Gets the names of all user created tables in the database as a ResultSet
     * @return The ResultSet, which should be closed after usage
     */
    public static ResultSet getTableNames() throws SQLException {

        String sql = "SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite%';";
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);

    }

    /**
     * Ends an SQL transaction and commits the results to file. Call after every change to the database
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

    /**
     * Converts the given ResultSet to an arrayList of type Crimes.
     * This method also closes the ResultSet.
     * @param data The ResultSet
     * @return An arraylist of Crimes
     * @throws SQLException If called on a ResultSet that does not contain crimes
     */
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

        data.close();

        return out;
    }

    /**
     * Executes the given SQL query on the database
     * @param query The Query
     * @return A ResultSet from the database
     * @throws SQLException If the query is erroneous
     */
    public static ResultSet executeQuery(String query) throws SQLException {

        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * Drops a table
     * @param tableName The name of the table to drop
     * @throws SQLException If the table does not exist
     */
    public static void dropTable(String tableName) throws SQLException {

        String sql = "DROP TABLE " + tableName + ";";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        endTransaction();
    }

    public static String getJdbcUrl() {
        return jdbcUrl;
    }

    public static Connection getConnection() {
        return connection;
    }
}
