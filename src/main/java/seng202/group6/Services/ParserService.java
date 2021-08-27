package seng202.group6.Services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import seng202.group6.Models.Crime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public static ArrayList<Crime> csvToArrayList(File file) throws IOException, CsvValidationException {

        ArrayList<Crime> crimeList = new ArrayList<Crime>();

        CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).build();

        int counter = 0;
        while(reader.peek() != null) {
            String[] fields = reader.readNext();

            try {
                Crime toBeAdded = buildCrimeFromFields(fields);
                crimeList.add(toBeAdded);
            } catch (Exception e) {
                counter++;
            }
        }
        System.out.println("%d entries skipped due to malformed record in CSV".format(String.valueOf(counter))); //TODO: Return this to caller somehow so it can be displayed in GUI

        return crimeList;
    }

    /**
     * Creates a new crime object based on the data in fields
     * @param fields A String[] with the data from the CSV
     * @return The crime
     */
    private static Crime buildCrimeFromFields(String[] fields) {
        Crime crime = new Crime (
                fields[0], //Case Num
                fields[1], //Date
                fields[2], //Block
                fields[3], //IUCR
                fields[4], //Primary Description
                fields[5], //Secondary Description
                fields[7], //Arrest
                fields[8], //Domestic
                Integer.parseInt(fields[9]), //Beat
                Integer.parseInt(fields[10]), //Ward
                fields[11], //FBI
                fields[6],  //Location Description
                fields[14], //Latitude
                fields[15]  //Longitude
        );
        return crime;
    }
}
