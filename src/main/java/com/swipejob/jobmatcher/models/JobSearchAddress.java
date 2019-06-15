package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSearchAddress {
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("maxJobDistance")
    private long maxJobDistance;
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("latitude")
    private double latitude;

    public JobSearchAddress() {
    }

    public long getMaxJobDistance() {
        return maxJobDistance;
    }

    public void setMaxJobDistance(long maxJobDistance) {
        this.maxJobDistance = maxJobDistance;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
