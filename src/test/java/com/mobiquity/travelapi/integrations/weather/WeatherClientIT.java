package com.mobiquity.travelapi.integrations.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest()
class WeatherClientIT {

    @Autowired
    private WeatherClient weatherClient;
    private ResponseEntity<Weather> actualWeatherResponse;

    @BeforeEach
    void setUp() {
        String latitude = "52.166542";
        String longitude = "4.483028";
        String dateTime = "1570458300";
        actualWeatherResponse = weatherClient.getDarkSkyResponse(longitude, latitude, dateTime);
    }

//    @Test
    void checkDarkSkyResponseCode() {
        assertEquals(200, actualWeatherResponse.getStatusCode().value());
    }

//    @Test
    void weatherNotNull() {
        assertNotNull(actualWeatherResponse.getBody());
    }
}
