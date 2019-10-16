package com.nsapplication.api.travelapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Trip {

    private List<Object> trips;
    private String scrollRequestBackwardContext;
    private  String scrollRequestForwardContext;

}
