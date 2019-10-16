package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.Trip;
import org.junit.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

public class NsRestClientTest {


    @Test
    public void checkRestTemplateHasKey() {
        NsRestClient nsRestClient = new NsRestClient();
        Trip trip;
        String uriString = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?originEVACode=8400282&destinationEVACode=8400056&dateTime=2019-10-07T16L25:00+0200";
        URI uri = URI.create(uriString);

        String keyValue = "a3db7e8808944380b20408e9742c86ab";
        trip = nsRestClient.getTrips(uri, keyValue);
        assertNotNull(trip);

    }

}