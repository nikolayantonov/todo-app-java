package com.mobiquity.travelapi.rest;

import com.mobiquity.travelapi.TravelService;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
public class TravelApiController {

    @Autowired
    private TravelService travelService;

    @PostMapping(value = "trips", consumes = "application/json")
    public @ResponseBody
    AllRoutesResponse postAllAvailableRoutes(@RequestBody TravelRequest travelRequest) {

        return MapTravelPlanToAllRoutesResponse.mapToAllRoutesResponse(
                travelService.getTravelResponse(travelRequest));
    }

}
