package com.mobiquity.travelapi.rest.userresponsemodels;

import com.mobiquity.travelapi.integrations.weather.Weather;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

//model class to show response to user
@Builder
@Getter
public class AllRoutesResponse {

    private String origin;
    private String destination;
    private List<RouteWeather> availableRoutes;

    @Builder
    @Getter
    static class RouteWeather {
        private String startTime;
        private String arrivalTime;
        private int duration;
        private int numberOfLegs;
        private Weather weatherAtDestination;

        @Builder
        @Getter
        static class Weather {
            private String summary;
            private double temperature;
            private double humidity;
            private double windSpeed;
        }
    }

}
