package com.mobiquity.travelapi.integrations.nsclient;

import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NsClientTest {

    private String expectedKeyName;
    private String expectedKeyValue;
    private String nsBaseUri;
    private String uriPath;
    private NsClient nsClient;

    @BeforeEach
    void setUp() {
        expectedKeyName = "Ocp-Apim-Subscription-Key";
        expectedKeyValue = "a3db7e8808944380b20408e9742c86ab";
        nsBaseUri = "gateway.apiportal.ns.nl/public-reisinformatie/api/v3";
        uriPath = "trips";

        nsClient = new NsClient(expectedKeyName, expectedKeyValue,
                nsBaseUri, uriPath);
    }

    @Test
    void keyNameIsPopulatedOnLoadingWithConfigValue() {
        assertTrue(nsClient.getHttpEntity().getHeaders().containsKey(expectedKeyName));
    }

    @Test
    void keyValueIsPopulatedOnLoadingWithConfigValue() {
        assertEquals(expectedKeyValue, nsClient.getHttpEntity().getHeaders().getFirst(expectedKeyName));
    }

    @Test
    void keyValueIsPopulatedOnLoadingWithConfigValueInvalid() {
        assertNotEquals(UUID.randomUUID().toString(), nsClient.getHttpEntity().getHeaders().getFirst(expectedKeyName));
    }

    /** Tests for URI */
    @Test
    void uriBaseUriEqualsExpectedBaseUri() {
        assertEquals(nsBaseUri, nsClient.getUriBase());
    }

    @Test
    void uriBaseUriEqualsExpectedBaseUriInvalid() {
        assertNotEquals(UUID.randomUUID().toString(), nsClient.getUriBase());
    }

    @Test
    void uriPathEqualsExpectedPath() {
        assertEquals(uriPath, nsClient.getUriPath());
    }

    @Test
    void uriPathEqualsExpectedPathInvalid() {
        assertNotEquals(UUID.randomUUID().toString(), nsClient.getUriBase());
    }

    @Test
    void fullUriConsumesInputInCreateUriMethod() {
        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16:25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();

        String expectedUri = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?originEVACode=8400282&destinationEVACode=8400056&dateTime=2019-10-07T16:25:00+0200";
        assertEquals(expectedUri, nsClient.buildUri(travelRequest).toString());
    }

    /** Tests for HttpEntity creation */
    @Test
    void httpEntityHasHeader() {
        assertEquals(expectedKeyValue, nsClient.getHttpEntity()
                .getHeaders().getFirst(expectedKeyName));
    }
    @Test
    void testThatHeaderIsNotNull() {
        assertFalse(nsClient.getHttpEntity().getHeaders().isEmpty());
    }


}