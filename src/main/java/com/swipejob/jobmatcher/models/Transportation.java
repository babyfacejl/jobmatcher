package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Transportation {
    CAR("CAR"),
    PUBLIC_TRANSPORT("PUBLIC TRANSPORT");

    private static Map<String, Transportation> nameMap = new HashMap<>(Transportation.values().length);

    private String value;

    Transportation(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Transportation forValue(String value) {
        return nameMap.get(value);
    }

    static {
        for (Transportation transportation : Transportation.values()) {
            nameMap.put(transportation.getValue(), transportation);
        }
    }
}
