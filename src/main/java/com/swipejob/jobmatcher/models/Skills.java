package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Skills {
    CREATOR_OF_OPPORTUNITIES("Creator of opportunities"),
    ARTS_AND_CRAFTS_DESIGNER("Arts and Crafts Designer");

    private static Map<String, Skills> nameMap = new HashMap<>(Skills.values().length);

    private String value;

    Skills(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Skills forValue(String value) {
        return nameMap.get(value);
    }

    static {
        for (Skills skill : Skills.values()) {
            nameMap.put(skill.getValue(), skill);
        }
    }

}
