package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
class NsRestClient {
    private static Logger log = LoggerFactory.getLogger(NsRestClient.class);

    private RestTemplate restTemplate = new RestTemplate();

    Trip getTrips(URI uri, String keyValue) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Ocp-Apim-Subscription-Key", keyValue);

        HttpHeaders httpHeaders = new HttpHeaders(map);
        HttpEntity<?> httpPackage = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<Trip> response = restTemplate.exchange(uri, HttpMethod.GET, httpPackage, Trip.class);

        log.info("GET request for Trips status :: " + response.getStatusCode().getReasonPhrase());

        return response.getBody();
    }
}
