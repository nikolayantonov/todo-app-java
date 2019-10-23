package com.mobiquity.travelapi.rest.userresponsemodels;

import com.mobiquity.travelapi.dto.RouteAndWeather;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;


public class MapTravelPlanToAllRoutesResponse {

    @Autowired
    private WeatherClient weatherClient;

    public static AllRoutesResponse mapToAllRoutesResponse(List<RouteAndWeather> travelResponse) {
        return new MapTravelPlanToAllRoutesResponse().map(travelResponse);
    }

    private AllRoutesResponse map(List<RouteAndWeather> travelResponse) {
        return AllRoutesResponse.builder()
                .origin(travelResponse.get(0).getRoute().getOrigin().getStationName())
                .destination(travelResponse.get(0).getRoute().getDestination().getStationName())
                .availableRoutes(getRouteWeather(travelResponse))
                .build();
    }

    private List<AllRoutesResponse.RouteWeather> getRouteWeather(List<RouteAndWeather> travelResponse) {
        List<AllRoutesResponse.RouteWeather> routeWeathers = new ArrayList<>();
        for (RouteAndWeather rw : travelResponse) {
            routeWeathers.add(
                    AllRoutesResponse.RouteWeather.builder()
                            .startTime(rw.getRoute().getOrigin().getPlannedDepartureTime())
                            .numberOfLegs(rw.getRoute().getLegs().size())
                            .duration(rw.getRoute().getPlannedDuration())
                            .arrivalTime(rw.getRoute().getDestination().getPlannedArrivalTime())
                            .weatherAtDestination(setWeather(rw))
                            .build()
            );
        }
        return routeWeathers;
    }

    private AllRoutesResponse.RouteWeather.Weather setWeather(RouteAndWeather rw) {
        return AllRoutesResponse.RouteWeather.Weather.builder()
                .summary(rw.getWeather().getCurrently().getSummary())
                .humidity(rw.getWeather().getCurrently().getHumidity())
                .temperature(rw.getWeather().getCurrently().getTemperature())
                .windSpeed(rw.getWeather().getCurrently().getWindSpeed())
                .build();
    }

}
