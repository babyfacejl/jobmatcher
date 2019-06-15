package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class Worker {
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("isActive")
    private boolean isActive;
    @JsonProperty("certificates")
    private List<String> certificates;
    @JsonProperty("skills")
    private List<String> skills;
    @JsonProperty("jobSearchAddress")
    private JobSearchAddress jobSearchAddress;
    @JsonProperty("transportation")
    private Transportation transportation;
    @JsonProperty("hasDriversLicense")
    private boolean hasDriversLicense;
    @JsonProperty("availability")
    private Availability[] availabilities;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("age")
    private int age;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("userId")
    private long userId;

    public Worker() {
    }

    public int getRating() {
        return rating;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public List<String> getSkills() {
        return skills;
    }

    public JobSearchAddress getJobSearchAddress() {
        return jobSearchAddress;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public boolean isHasDriversLicense() {
        return hasDriversLicense;
    }

    public Availability[] getAvailabilities() {
        return availabilities;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGuid() {
        return guid;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "rating=" + rating +
                ", isActive=" + isActive +
                ", certificates=" + certificates +
                ", skills=" + skills +
                ", jobSearchAddress=" + jobSearchAddress +
                ", transportation=" + transportation +
                ", hasDriversLicense=" + hasDriversLicense +
                ", availabilities=" + Arrays.toString(availabilities) +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name=" + name +
                ", age=" + age +
                ", guid='" + guid + '\'' +
                ", userId=" + userId +
                '}';
    }
}
