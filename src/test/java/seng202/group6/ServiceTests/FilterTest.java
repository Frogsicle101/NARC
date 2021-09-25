package seng202.group6.ServiceTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seng202.group6.Controllers.ImportController;
import seng202.group6.Services.Filter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    @BeforeAll
    public static void setUp() {
        ImportController.currentTable = "testTable";
    }


    @Test
    public void testBlankFilter() {
        Filter filter = new Filter();
        String query = filter.queryBuilder();

        String expected = "SELECT * FROM testTable;";

        assertEquals(expected, query);
    }

    @Test
    public void testFilter() {
        Filter filter = new Filter();

        LocalDate start = LocalDate.now().minusDays(5);
        LocalDate end = LocalDate.now();
        filter.setStart(start);
        filter.setEnd(end);

        HashSet<String> set = new HashSet<>();
        set.add("Test1");
        set.add("Test2");
        filter.setTypes(set);
        filter.setLocations(set);
        filter.setArrest(true);
        filter.setDomestic(false);
        filter.setBeats("12");
        filter.setWards("12, 13");


        String query = filter.queryBuilder();



        String expected = "SELECT * FROM testTable " +
                "WHERE occurrence_date >= '" + LocalDateTime.of(start, LocalTime.MIDNIGHT) + "' " +
                "AND occurrence_date < '" + LocalDateTime.of(end.plusDays(1), LocalTime.MIDNIGHT) + "' " +
                "AND primary_description IN ('Test1', 'Test2') " +
                "AND location IN ('Test1', 'Test2') " +
                "AND arrest = true " +
                "AND domestic = false " +
                "AND beat IN (12) " +
                "AND ward IN (12, 13);";

        assertEquals(expected, query);
    }







}
