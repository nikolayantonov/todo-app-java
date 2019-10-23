package com.mobiquity.travelapi.integrations.nsclient.travelmodel;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Route {

    private TripDetail origin;
    private TripDetail destination;
    private String dateTime;
    private int numberOfTransfers;
    private int plannedDuration;
    private List<Leg> legs;
    private int price;
}
