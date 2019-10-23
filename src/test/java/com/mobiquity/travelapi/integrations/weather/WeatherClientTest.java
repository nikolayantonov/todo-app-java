package com.mobiquity.travelapi.integrations.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherClientTest {
    private WeatherClient weatherClient;
    private final String keyValue = "somekeypassword";
    private final List<String> exclusions = List.of("hourly", "daily", "flags");
    private final String scheme = "https";

    @BeforeEach
    void setUp() {
        String baseUrl = "api.darksky.net/forecast/";
        weatherClient = new WeatherClient(keyValue, baseUrl, scheme, getExclusions());
    }

    @Test
    void weatherClientUrlIsGeneratedWithLocationInput() {
        String longitude = "4.483028";
        String latitude = "52.166542";
        String dateTime = "1570458300";

        URI expectedUri = URI.create(scheme + "://" + "api.darksky.net/forecast/"
                + keyValue + "/"
                + latitude + ","
                + longitude + ","
                + dateTime
                + "?exclude=" + getExclusions());

        URI actualUri = weatherClient.buildUri(dateTime, longitude, latitude);
        assertEquals(expectedUri, actualUri);
    }

    private String getExclusions() {
        StringBuilder exclusionString = new StringBuilder();
        int iterator = 0;
        for (String s : exclusions) {
            exclusionString.append(exclusions.get(iterator++));
            exclusionString.append(",");
        }
        exclusionString.deleteCharAt(exclusionString.length() - 1);
        return exclusionString.toString();
    }


}
