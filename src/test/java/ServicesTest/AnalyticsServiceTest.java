package ServicesTest;

import org.junit.jupiter.api.Test;
import seng202.group6.Models.Crime;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seng202.group6.Services.AnalyticsService.getDistanceBetween;

public class AnalyticsServiceTest {
    @Test
    public void getDistanceBetweenTest() {
        Crime crime1 = new Crime();
        Crime crime2 = new Crime();
        crime1.setLongitude(-43.540053);
        crime1.setLatitude(172.633589);
        crime2.setLongitude(-43.533884462594315);
        crime2.setLatitude(172.6332843037812);
        assertEquals(0.6863, getDistanceBetween(crime1, crime2));

    }

}
