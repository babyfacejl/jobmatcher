package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "availability")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Availability {
    @JsonProperty("title")
    private String title;
    @JsonProperty("dayIndex")
    private String dayIndex;

    public String getTitle() {
        return title;
    }

    public String getDayIndex() {
        return dayIndex;
    }
}
