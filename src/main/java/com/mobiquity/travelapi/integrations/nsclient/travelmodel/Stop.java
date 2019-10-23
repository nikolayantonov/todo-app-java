package com.mobiquity.travelapi.integrations.nsclient.travelmodel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Stop {

    private String name;
    private String longitude;
    private String latitude;
}
