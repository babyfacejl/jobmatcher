package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {
    @JsonProperty("last")
    private String last;
    @JsonProperty("first")
    private String first;

//    public Name(String last, String first) {
//        this.last = last;
//        this.first = first;
//    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
}
