package com.mobiquity.travelapi.dto;

import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.weather.Weather;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RouteAndWeather {
    private Route route;
    private Weather weather;
}
