package com.mobiquity.travelapi.integrations.nsclient.travelmodel;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Leg {

    private String startStation;
    private String departurePlatform;
    private String endStation;
    private String arrivalPlatform;
    private int numberOfStops;
    private List<Stop> stops;
    private String travelType;
}
