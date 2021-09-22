package seng202.group6.Services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import seng202.group6.Models.Crime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserService {
    /**
     * Gets the data from the given file and converts it into an arraylist.
     * @param file A file object in CSV form, containing a list of crimes
     * @throws IOException
     * @throws CsvValidationException
     */
    public static int csvToDatabase(File file, String tableName) throws IOException, CsvValidationException, SQLException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).build();
        int counter = 0;
        while(reader.peek() != null) {
            String[] fields = reader.readNext();

            Crime crime = buildCrimeFromFields(fields);
            try {
                SQLiteDatabase.insertIntoTable(tableName, crime);   //Populates "Crimes" table in database
            } catch (SQLException e) {
                counter++;
            }
        }
        SQLiteDatabase.endTransaction();
        return counter;
    }

    /**
     * Creates a new crime object based on the data in fields
     * @param fields A String[] with the data from the CSV
     * @return The crime
     */
    private static Crime buildCrimeFromFields(String[] fields) {
        Crime crime = new Crime (
                fields[0], //Case Num
                parseDateString(fields[1]), //Date
                fields[2], //Block
                fields[3], //IUCR
                fields[4], //Primary Description
                fields[5], //Secondary Description
                fields[7].equals("Y"), //Arrest
                fields[8].equals("Y"), //Domestic
                fields[9].equals("") ? -1 : Integer.parseInt(fields[9]), //Beat
                fields[10].equals("") ? -1 : Integer.parseInt(fields[10]), //Ward
                fields[11], //FBI
                fields[6],  //Location Description
                fields[14].equals("") ? 0.0 : Double.parseDouble(fields[14]), //Latitude
                fields[15].equals("") ? 0.0 : Double.parseDouble(fields[15]) //Longitude
        );
        return crime;
    }

    /**
     * This method takes a string representing a date of format MM/DD/YYYY Hour/Minute/Second AM/PM and converts it
     * into an object of type LocalDateTime to represent it
     * @param date A string representing a date of format MM/DD/YYYY Hour/Minute/Second AM/PM
     * @return Returns a LocalDateTime object representing the date-time passed to it as a string.
     */
    private static LocalDateTime parseDateString(String date) {
        int second = Integer.parseInt(date.substring(17, 19));
        int minute = Integer.parseInt(date.substring(14, 16));
        int hour = Integer.parseInt(date.substring(11, 13));
        int day = Integer.parseInt(date.substring(3, 5));
        int month = Integer.parseInt(date.substring(0, 2));
        int year = Integer.parseInt(date.substring(6, 10));
        if (date.endsWith("PM")){
            hour += 12;
            if(hour == 24){
                hour = 0;
            }
        }

        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);

        return dateTime;
    }
}
