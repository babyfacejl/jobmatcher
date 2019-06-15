package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {
    @JsonProperty("last")
    private String last;
    @JsonProperty("first")
    private String first;

    public String getLast() {
        return last;
    }

    public String getFirst() {
        return first;
    }

}
