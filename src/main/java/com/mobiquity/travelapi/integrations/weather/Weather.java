package com.mobiquity.travelapi.integrations.weather;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Weather {

    @JsonAlias
    private Currently currently;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    public static class Currently {
        private String summary;
        private double temperature;
        private double humidity;
        private double windSpeed;
    }

}
