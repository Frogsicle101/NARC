package seng202.group6.ServiceTests;


import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;
import seng202.group6.Services.ParserService;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserServiceTest {

    @Test
    public void parseDateTest() {
        String date = "06/15/2021 09:30:00 AM";
        LocalDateTime returnObject = ParserService.parseDateString(date);
        LocalDateTime correctDate = LocalDateTime.of(2021, 6, 15, 9, 30, 0);
        assertEquals(returnObject, correctDate);
    }

    @Test
    public void buildCrimeTest() {
        String[] crimeInfo = new String[16];

        crimeInfo[0] = "JE266628";
        crimeInfo[1] = "06/15/2021 09:30:00 AM";
        crimeInfo[2] = "080XX S DREXEL AVE";
        crimeInfo[3] = "0820";
        crimeInfo[4] = "THEFT";
        crimeInfo[5] = "$500 AND UNDER";
        crimeInfo[6] = "STREET";
        crimeInfo[7] = "N";
        crimeInfo[8] = "N";
        crimeInfo[9] = "631";
        crimeInfo[10] = "8";
        crimeInfo[11] = "06";
        crimeInfo[14] = "41.748486365";
        crimeInfo[15] = "-87.602675062";

        Crime builtCrime = ParserService.buildCrimeFromFields(crimeInfo);

        Crime correctCrime = new Crime("JE266628",
                ParserService.parseDateString("06/15/2021 09:30:00 AM"),
                "080XX S DREXEL AVE",
                "0820",
                "THEFT",
                "$500 AND UNDER",
                false,
                false,
                631,
                8,
                "06",
                "STREET",
                41.748486365,
                -87.602675062);

        assertEquals(correctCrime, builtCrime);
    }

    @Test
    public void buildCrimeWithEmptyFieldsTest() {
        String[] crimeInfo = new String[16];

        crimeInfo[0] = "JE266628";
        crimeInfo[1] = "06/15/2021 09:30:00 AM";
        crimeInfo[2] = "";
        crimeInfo[3] = "0820";
        crimeInfo[4] = "THEFT";
        crimeInfo[5] = "$500 AND UNDER";
        crimeInfo[6] = "STREET";
        crimeInfo[7] = "N";
        crimeInfo[8] = "N";
        crimeInfo[9] = "631";
        crimeInfo[10] = "8";
        crimeInfo[11] = "";
        crimeInfo[14] = "41.748486365";
        crimeInfo[15] = "-87.602675062";

        Crime builtCrime = ParserService.buildCrimeFromFields(crimeInfo);

        Crime correctCrime = new Crime("JE266628",
                ParserService.parseDateString("06/15/2021 09:30:00 AM"),
                "",
                "0820",
                "THEFT",
                "$500 AND UNDER",
                false,
                false,
                631,
                8,
                "",
                "STREET",
                41.748486365,
                -87.602675062);

        assertEquals(correctCrime, builtCrime);

    }

    @Test
    public void buildFieldsFromCrime() {
        String[] expected = new String[17];
        Arrays.fill(expected, "");

        expected[0] = "JE266628";
        expected[1] = "06/15/2021 09:30:00 AM";
        expected[2] = "080XX S DREXEL AVE";
        expected[3] = "0820";
        expected[4] = "THEFT";
        expected[5] = "$500 AND UNDER";
        expected[6] = "STREET";
        expected[7] = "N";
        expected[8] = "N";
        expected[9] = "631";
        expected[10] = "8";
        expected[11] = "06";
        expected[14] = "41.748486365";
        expected[15] = "-87.602675062";
        expected[16] = "(41.748486365, -87.602675062)";

        Crime crime = new Crime("JE266628",
                ParserService.parseDateString("06/15/2021 09:30:00 AM"),
                "080XX S DREXEL AVE",
                "0820",
                "THEFT",
                "$500 AND UNDER",
                false,
                false,
                631,
                8,
                "06",
                "STREET",
                41.748486365,
                -87.602675062);

        String[] actual = ParserService.buildFieldsFromCrime(crime);


        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }

        assertArrayEquals(expected, actual);

    }

    @Test
    public void convertAndBackAgain() {
        Crime crime = new Crime("JE266628",
                ParserService.parseDateString("06/15/2021 09:30:00 AM"),
                "080XX S DREXEL AVE",
                "0820",
                "THEFT",
                "$500 AND UNDER",
                false,
                false,
                631,
                8,
                "06",
                "STREET",
                41.748486365,
                -87.602675062);

        String[] fields = ParserService.buildFieldsFromCrime(crime);
        Crime actual = ParserService.buildCrimeFromFields(fields);

        assertEquals(crime, actual);

    }

}