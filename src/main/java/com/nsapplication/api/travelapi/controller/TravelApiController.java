package com.nsapplication.api.travelapi.controller;

import com.nsapplication.api.travelapi.TravelService;
import com.nsapplication.api.travelapi.model.TravelRequest;
import com.nsapplication.api.travelapi.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/")
public class TravelApiController {

    private static Logger logger = LoggerFactory.getLogger(TravelApiController.class);
    private TravelService travelService = new TravelService();

    @RequestMapping(name = "trips", method = RequestMethod.POST, consumes = "application/json")
    public Trip processUserRequest(@RequestBody TravelRequest travelRequest) {
        logger.info("Inside controller");
        logger.info(travelRequest.getDateTime() + "  " + travelRequest.getOriginEVACode() + "  " + travelRequest.getDestinationEVACode());

        return travelService.travelHandler(travelRequest);
    }

}
