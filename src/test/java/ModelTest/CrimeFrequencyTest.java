package ModelTest;

import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;
import seng202.group6.Models.CrimeFrequency;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrimeFrequencyTest {

    @Test
    public void testContains() {
        CrimeFrequency crime1 = new CrimeFrequency("Theft");
        CrimeFrequency crime2 = new CrimeFrequency("Theft");

        ArrayList<CrimeFrequency> testList = new ArrayList<>();

        testList.add(crime1);

        assertTrue(testList.contains(crime2));

    }

    @Test
    public void testEquals() {
        CrimeFrequency crime1 = new CrimeFrequency("Burglary");
        CrimeFrequency crime2 = new CrimeFrequency("Burglary");

        assertEquals(crime1, crime2);
    }

    @Test
    public void testIncrementFrequency() {
        CrimeFrequency crime1 = new CrimeFrequency("Stealing");

        crime1.incrementFrequency();
        crime1.incrementFrequency();

        assertEquals(crime1.getFrequency(), 3);
    }

}
