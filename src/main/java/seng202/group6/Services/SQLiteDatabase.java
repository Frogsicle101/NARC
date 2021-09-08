package seng202.group6.Services;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
     * @param fields The fields to insert into the table
     */
    public static void insertIntoTable(String tableName, String[] fields) throws SQLException {
        String sqlInsert ="INSERT INTO " + tableName + " VALUES (";

        String coordinates;
        if(fields[14].equals("") || fields[15].equals("")){
            coordinates = ",NULL, NULL";
        } else {
            coordinates = ", " + fields[14] + ", " + fields[15];
        }


        String sql = sqlInsert + "'" + fields[0] + "', " +  //case_id
                "'" + fields[1] + "', " +   //occurrence_date
                "'" + fields[2] + "', " +   //block
                "'" + fields[3] + "', " +   //iucr
                "'" + fields[4] + "', " +   //primary_description
                "'" + fields[5] + "', " +   //secondary_description
                "'" + fields[6] + "', " +   //location_description
                fields[7].equals("Y") + ", " +  //arrest
                fields[7].equals("Y") + ", " +  //domestic
                Integer.parseInt(fields[9]) + ", " +    //beat
                Integer.parseInt(fields[10]) + ", " +   //ward
                "'" + fields[11] + "'" +  //fbi_cd
                coordinates + ")";

        Statement statement = connection.createStatement();

        try {
            statement.executeUpdate(sql);
        } catch(SQLException e) {}

    }

    /**
     * Select all data from a table
     * @param tableName The name of the table to select from
     */
    public static ResultSet selectAllFromTable(String tableName) throws SQLException {
        String sql = "SElECT * FROM "+tableName;

        Statement statement = connection.createStatement();
        ResultSet allData = statement.executeQuery(sql);

        return allData;
    }

    public static ArrayList<Crime> convertResultSet(ResultSet data) throws SQLException {
        ArrayList<Crime> out = new ArrayList<>();

        while (data.next()) {
            Crime newCrime = new Crime(data.getString("case_id"),
                    data.getString("occurrence_date"),
                    data.getString("block"),
                    data.getString("iucr"),
                    data.getString("primary_description"),
                    data.getString("secondary_description"),
                    data.getBoolean("arrest"),
                    data.getBoolean("domestic"),
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
}
