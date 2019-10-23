package com.mobiquity.travelapi;

import com.mobiquity.travelapi.dto.RouteAndWeather;
import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Leg;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Stop;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TravelService {

    @Autowired
    private NsClient nsClient;
    @Autowired
    private WeatherClient weatherClient;

    public List<RouteAndWeather> getTravelResponse(TravelRequest travelRequest) {
        List<RouteAndWeather> travelResponse = new ArrayList<>();
        for (Route route : nsClient.getTravelPlan(travelRequest).getRoutes()) {
            Stop lastStopOfRoute = getLastStop(route);
            travelResponse.add(RouteAndWeather.builder()
                    .route(route)
                    .weather(weatherClient.getDarkSkyResponse(
                            getLongitude(lastStopOfRoute),
                            getLatitude(lastStopOfRoute),
                            getDateTime(route)).getBody())
                    .build());
        }
        return travelResponse;
    }

    private String getDateTime(Route route) {
        return getEpochTime(route.getOrigin().getPlannedArrivalTime());
    }

    private String getLongitude(Stop lastStop) {
        return lastStop.getLongitude();
    }

    private String getLatitude(Stop lastStop) {
        return lastStop.getLatitude();
    }

    private Stop getLastStop(Route route) {
        Leg lastLeg = getLastLeg(route);
        return lastLeg.getStops().get(lastLeg.getStops().size() - 1);
    }

    private Leg getLastLeg(Route route) {
        return route.getLegs().get(route.getLegs().size() - 1);
    }

    String getEpochTime(String dateTime) {
        ZonedDateTime zdt = ZonedDateTime.parse(correctDateTime(dateTime));
        return String.valueOf(zdt.toEpochSecond());
    }

    private String correctDateTime(String dateTime) {
        StringBuilder sb = new StringBuilder().append(dateTime);
        sb.delete(sb.indexOf("+") + 3, sb.length());
        return sb.append(":00").toString();
    }
}
