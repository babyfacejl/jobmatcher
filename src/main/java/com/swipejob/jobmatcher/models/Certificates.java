package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Certificates {
    EXCELLENCE_IN_ORGANIZATION("Excellence in Organization"),
    OUTSTANDING_INNOVATOR("Outstanding Innovator"),
    THE_BEHIND_THE_SCENES_WONDER("Outstanding Innovator"),
    THE_RISK_TAKER("The Risk Taker"),
    OUTSIDE_THE_BOX_THINKER("Outside the Box Thinker"),
    MARVELOUS_MULTITASKER("Marvelous Multitasker"),
    THE_ASKER_OF_GOOD_QUESTIONS("The Asker of Good Questions"),
    OUTSTANDING_MEMORY_AWARD("Outstanding Memory Award"),
    THE_HUMAN_HANDBOOK("The Human Handbook"),
    OFFICE_LUNCH_EXPERT("Office Lunch Expert");

    private static Map<String, Certificates> nameMap = new HashMap<>(Certificates.values().length);

    private String value;

    Certificates(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Certificates forValue(String value) {
        return nameMap.get(value);
    }

    static {
        for (Certificates certificate : Certificates.values()) {
            nameMap.put(certificate.getValue(), certificate);
        }
    }
}
