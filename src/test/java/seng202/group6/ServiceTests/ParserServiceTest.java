package seng202.group6.ServiceTests;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;
import seng202.group6.Services.ParserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserServiceTest {

    @BeforeAll
    public static void setUp() {
        try {
            File testFile = new File("test.csv");
            testFile.deleteOnExit();
            FileWriter writer = new FileWriter(testFile);
            writer.write("CASE#,DATE  OF OCCURRENCE,BLOCK, IUCR, PRIMARY DESCRIPTION, SECONDARY DESCRIPTION, " +
                    "LOCATION DESCRIPTION,ARREST,DOMESTIC,BEAT,WARD,FBI CD,X COORDINATE,Y COORDINATE,LATITUDE,LONGITUDE,LOCATION" +

                    "\nJE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE,0820,THEFT,$500 AND UNDER,STREET,N,N,631,8,06," +
                    "1183633,1851786,41.748486365,-87.602675062,\"(41.748486365, -87.602675062)\"");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Hi");
        }
    }

    @Test
    public void csvToArrayList() throws IOException, CsvValidationException {
        ArrayList<Crime> generatedCrimes = ParserService.csvToArrayList(new File("test.csv"));
        Crime expected = new Crime(
                "JE266628",
                "06/15/2021 09:30:00 AM",
                "080XX S DREXEL AVE",
                "0820",
                "THEFT",
                "$500 AND UNDER",
                "N",
                "N",
                631,
                8,
                "061183633",
                "STREET",
                "41.880660786",
                "-87.731186405"
        );
        assert(expected.getCaseNumber().equals(generatedCrimes.get(0).getCaseNumber()));

    }

    @AfterAll
    public static void tearDown() {
        (new File("test.csv")).delete();
    }
}