package com.nsapplication.api.travelapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelRequest {

    private String originEVACode;
    private String destinationEVACode;
    private String dateTime;

    public TravelRequest(String originEVACode, String destinationEVACode, String dateTime) {
        this.originEVACode = originEVACode;
        this.destinationEVACode = destinationEVACode;
        this.dateTime = dateTime;
    }
}
