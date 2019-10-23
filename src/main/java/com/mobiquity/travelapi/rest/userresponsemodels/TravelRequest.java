package com.mobiquity.travelapi.rest.userresponsemodels;

import lombok.Getter;

@Getter
public class TravelRequest {
    private String dateTime;
    private String destinationEVACode;
    private String originEVACode;

    private TravelRequest() {
    }

    public static class Builder {
        private String dateTime;
        private String destinationEVACode;
        private String originEVACode;

        public Builder dateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder destinationEvaCode(String destinationEVACode) {
            this.destinationEVACode = destinationEVACode;
            return this;
        }

        public Builder originEvaCode(String originEVACode) {
            this.originEVACode = originEVACode;
            return this;
        }

        public TravelRequest build() {
            TravelRequest travelRequest = new TravelRequest();
            travelRequest.dateTime = this.dateTime;
            travelRequest.destinationEVACode = this.destinationEVACode;
            travelRequest.originEVACode = this.originEVACode;

            return travelRequest;
        }
    }

}
