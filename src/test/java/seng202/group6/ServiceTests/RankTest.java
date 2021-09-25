package seng202.group6.ServiceTests;

import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;
import seng202.group6.Models.TimeFrequency;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seng202.group6.Services.Rank.rankedTimeList;

public class RankTest {

    @Test
    public void testTimeRank() {
        ArrayList<Crime> crimes = new ArrayList<>();
        crimes.add(new Crime("JE163990", LocalDateTime.of(2020, 11, 23, 03, 05, 00), "073XX S SOUTH SHORE DR",
                "820", "THEFT", "$500 AND UNDER", true, false,
                0,334, "7", "6", 11.23, 13133.21));
        crimes.add(new Crime("JE163990", LocalDateTime.of(2020, 11, 23, 03, 05, 00), "073XX S SOUTH SHORE DR",
                "820", "THEFT", "$500 AND UNDER", true, false,
                0,334, "7", "6", 11.23, 13133.21));
        crimes.add(new Crime("JE163990", LocalDateTime.of(2020, 11, 23, 05, 05, 00), "073XX S SOUTH SHORE DR",
                "820", "THEFT", "$500 AND UNDER", true, false,
                0,334, "7", "6", 11.23, 13133.21));

        ArrayList<TimeFrequency> hourly = rankedTimeList(crimes, 0);
        ArrayList<TimeFrequency> weekly = rankedTimeList(crimes, 0);
        ArrayList<TimeFrequency> yearly = rankedTimeList(crimes, 0);

        ArrayList<TimeFrequency> expectedhourly = new ArrayList<>();
        TimeFrequency three = new TimeFrequency(3);
        three.setFrequency(2);

        expectedhourly.add(three);
        expectedhourly.add(new TimeFrequency(5));

        assert(expectedhourly.equals(hourly));
    }


}
