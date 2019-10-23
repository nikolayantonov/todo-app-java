package com.mobiquity.travelapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelServiceTest {

    private TravelService travelService = new TravelService();
    private final String dateTime = "2019-10-07T16:25:00+0200";

    @Test
    void getEpochTimeFromDateTime() {
        String expected = "1570458300";
        String actual = travelService.getEpochTime(dateTime);

        assertEquals(expected, actual);
    }
}
