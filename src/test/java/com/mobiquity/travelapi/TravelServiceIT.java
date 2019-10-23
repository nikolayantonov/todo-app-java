package com.mobiquity.travelapi;

import com.mobiquity.travelapi.dto.RouteAndWeather;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
class TravelServiceIT {

    @Autowired
    private TravelService travelService;
    @Autowired
    private WeatherClient weatherClient;
    private TravelRequest travelRequest;

    @BeforeEach
    void setup() {
        travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16:25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();
    }

//    @Test
    void listOfRoutesAndWeatherIs6() {
        int expectedSize = 6;
        assertEquals(expectedSize, travelService.getTravelResponse(travelRequest).size());
        for (RouteAndWeather rw : travelService.getTravelResponse(travelRequest)) {
            assertNotNull(rw);
        }
    }

//    @Test
    void listOfRoutesAndWeatherIsInvalid() {
        int invalidSize = 23423424;
        assertNotEquals(invalidSize, travelService.getTravelResponse(travelRequest).size());
    }

}