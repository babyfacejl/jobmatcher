package com.swipejob.jobmatcher.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Job {
    @JsonProperty("driverLicenseRequired")
    private boolean driverLicenseRequired;
    @JsonProperty("requiredCertificates")
    private List<String> requiredCertificates;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("billRate")
    private String billRate;
    @JsonProperty("workersRequired")
    private int workersRequired;
    @JsonProperty("startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
    private Date startDate;
    @JsonProperty("about")
    private String about;
    @JsonProperty("jobTitle")
    private String jobTitle;
    @JsonProperty("company")
    private String company;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("jobId")
    private long jobId;

    public Job() {
    }

    public boolean isDriverLicenseRequired() {
        return driverLicenseRequired;
    }

    public List<String> getRequiredCertificates() {
        return requiredCertificates;
    }

    public Location getLocation() {
        return location;
    }

    public String getBillRate() {
        return billRate;
    }

    public double getBillRateAsDouble() {
        return Double.valueOf(billRate.substring(1));
    }

    public int getWorkersRequired() {
        return workersRequired;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getAbout() {
        return about;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getGuid() {
        return guid;
    }

    public long getJobId() {
        return jobId;
    }

    @Override
    public String toString() {
        return "Job{" +
                "driverLicenseRequired=" + driverLicenseRequired +
                ", requiredCertificates=" + requiredCertificates +
                ", location=" + location +
                ", billRate='" + billRate + '\'' +
                ", workersRequired=" + workersRequired +
                ", startDate=" + startDate +
                ", about='" + about + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", company='" + company + '\'' +
                ", guid='" + guid + '\'' +
                ", jobId=" + jobId +
                '}';
    }

}
