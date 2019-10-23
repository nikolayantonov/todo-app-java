package com.mobiquity.travelapi.integrations.nsclient;

import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Getter(AccessLevel.PROTECTED)
public class NsClient {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * variables should be final
     */
    private String uriBase;
    private String uriPath;
    private HttpEntity httpEntity;

    NsClient(@Value("${authentication.key.name.ns}") String keyName,
             @Value("#{environment.NSKEY}") String keyValue,
             @Value("${urls.base.ns}") String uriBase,
             @Value("${urls.path.ns}") String uriPath) {
        this.uriBase = uriBase;
        this.uriPath = uriPath;
        this.httpEntity = createHttpEntity(keyName, keyValue);
    }

    private HttpEntity<?> createHttpEntity(String keyName, String keyValue) {
        return new HttpEntity<>(createHttpHeader(keyName, keyValue));
    }

    private HttpHeaders createHttpHeader(String keyName, String keyValue) {
        MultiValueMap<String, String> mvMap = new LinkedMultiValueMap<>() {{
            add(keyName, keyValue);
        }};
        return new HttpHeaders(mvMap);
    }

    public TravelPlan getTravelPlan(TravelRequest travelRequest) {
        return TravelModelMapper.mapToTravelPlan(
                getNsResponse(travelRequest).getBody()
        );
    }

    ResponseEntity<NsResponse> getNsResponse(TravelRequest travelRequest) {
        return restTemplate.exchange(buildUri(travelRequest), HttpMethod.GET, httpEntity, NsResponse.class);
    }

    URI buildUri(TravelRequest travelRequest) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(uriBase).path(uriPath)
                .queryParam("originEVACode", travelRequest.getOriginEVACode())
                .queryParam("destinationEVACode", travelRequest.getDestinationEVACode())
                .queryParam("dateTime", travelRequest.getDateTime())
                .build();

        return uri.toUri();
    }

}
