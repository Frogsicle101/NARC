package ModelTest;


import org.junit.jupiter.api.Test;
import seng202.group6.Models.AreaFrequency;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class AreaFrequencyTest {

    @Test
    public void testConstruction() {
        AreaFrequency areaFrequency = new AreaFrequency("123");
        assertEquals("123", areaFrequency.getArea());
    }

    @Test
    public void testEquals() {
        AreaFrequency areaFrequency1 = new AreaFrequency("123");
        AreaFrequency areaFrequency2 = new AreaFrequency("123");
        for (int i = 0; i < 6; i++) {
            areaFrequency1.incrementFrequency();
        }
        assertTrue(areaFrequency1.equals(areaFrequency2));
    }

}
