package com.mobiquity.travelapi.integrations.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherClient {

    @Autowired
    private RestTemplate restTemplate;
    private HttpEntity httpEntity;
    private final String uriBase;
    private final String key;
    private final String scheme;
    private final String exclusions;

    WeatherClient(@Value("#{environment.DARKKEY}") String key,
                  @Value("${urls.base.darkSky}") String uriBase,
                  @Value("${urls.scheme.darkSky}") String scheme,
                  @Value("${urls.parameters.exclusions.darkSky}") String exclusions) {
        this.key = key;
        this.uriBase = uriBase;
        this.scheme = scheme;
        this.exclusions = exclusions;
    }

    public ResponseEntity<Weather> getDarkSkyResponse(String longitude, String latitude, String dateTime) {
        return restTemplate.getForEntity(
                buildUri(dateTime, longitude, latitude), Weather.class);
    }

    URI buildUri(String dateTime, String longitude, String latitude) {
        return UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(extendUri(dateTime, longitude, latitude))
                .queryParam("exclude", exclusions)
                .build()
                .toUri();
    }

    private String extendUri(String dateTime, String longitude, String latitude) {
        return (uriBase + key + "/" + latitude + "," + longitude + "," + dateTime);
    }


}
