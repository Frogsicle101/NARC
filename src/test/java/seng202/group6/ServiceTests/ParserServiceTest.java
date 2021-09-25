package seng202.group6.ServiceTests;


import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;
import seng202.group6.Services.ParserService;

import java.time.LocalDateTime;

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


    /*
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

     */
    //TODO Fix this
    /*@Test
    public void csvToArrayList() throws IOException, CsvValidationException, SQLException {
        ArrayList<Crime> generatedCrimes = ParserService.csvToArrayList(new File("test.csv"));
        Crime expected = new Crime(
                "JE266628",
                "06/15/2021 09:30:00 AM",
                "080XX S DREXEL AVE",
                "0820",
                "THEFT",
                "$500 AND UNDER",
                "STREET",
                "N",
                "N",
                631,
                8,
                "061183633",
                "41.880660786",
                "-87.731186405"
        );
        assert(expected.getCaseNumber().equals(generatedCrimes.get(0).getCaseNumber()));

    }

    @AfterAll
    public static void tearDown() {
        (new File("test.csv")).delete();
    }
    */

}