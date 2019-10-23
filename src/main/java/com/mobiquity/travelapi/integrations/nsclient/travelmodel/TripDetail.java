package com.mobiquity.travelapi.integrations.nsclient.travelmodel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TripDetail {

    private String stationName;
    private String plannedDepartureTime;
    private String plannedArrivalTime;
    private String plannedTrack;
}
