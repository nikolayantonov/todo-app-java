package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.TravelRequest;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class TravelServiceTest {

    @Test
    public void checkURIIsGeneratedFromTravelRequestIsValid()
    {
        TravelService travelService = new TravelService();
        TravelRequest travelRequest = new TravelRequest("8400282","8400056","2019-10-07T16L25:00+0200");
        assertEquals("https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?originEVACode=8400282&destinationEVACode=8400056&dateTime=2019-10-07T16L25:00+0200",
                travelService.generateURI(travelRequest).toString());
    }


}