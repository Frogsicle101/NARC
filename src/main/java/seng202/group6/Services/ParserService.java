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
import java.util.ArrayList;
import java.util.Scanner;

public class ParserService {
    /**
     * Gets the data from the given file and converts it into an arraylist.
     * @param file A file object in CSV form, containing a list of crimes
     * @return An arraylist of Crime objects
     * @throws IOException
     * @throws CsvValidationException
     */
    public static ArrayList<Crime> csvToArrayList(File file) throws IOException, CsvValidationException, SQLException {

        ArrayList<Crime> crimeList = new ArrayList<Crime>();

        CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).build();

        int counter = 0;
        while(reader.peek() != null) {

            String[] fields = reader.readNext();
            SQLiteDatabase.insertIntoTable("Crimes", fields);   //Populates "Crimes" table in database

        }

        //TODO Get rid of or make use of these lines
        SQLiteDatabase.selectAllFromTable("Crimes");    //This returns a ResultSet but it isn't used
        return crimeList;       //This currently returns an empty arraylist
    }


}
