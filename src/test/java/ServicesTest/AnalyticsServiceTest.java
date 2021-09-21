package ServicesTest;

import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seng202.group6.Services.AnalyticsService.compareCrimeTimes;

public class AnalyticsServiceTest {
    @Test
    public void compareTimeTest() {
        Crime crime1 = new Crime();
        Crime crime2 = new Crime();
        LocalDateTime date1 = LocalDateTime.of(0, 1,1,0,0,0);
        LocalDateTime date2 = LocalDateTime.of(0, 2,2,0,0,5);
        crime1.setDate(date1);
        crime2.setDate(date2);
        LocalDateTime date3 = LocalDateTime.of(0, 1,1,0,0,5);
        assertEquals(date3, compareCrimeTimes(crime1, crime2));

    }
    
}
