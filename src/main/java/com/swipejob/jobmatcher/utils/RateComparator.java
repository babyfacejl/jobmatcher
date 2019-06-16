package com.swipejob.jobmatcher.utils;

import com.swipejob.jobmatcher.models.Job;

import java.util.Comparator;

public class RateComparator implements Comparator<Job> {
    @Override
    public int compare(Job o1, Job o2) {
        if (o1.getBillRateAsDouble() > o2.getBillRateAsDouble()) {
            return -1;
        }
        if (o1.getBillRateAsDouble() < o2.getBillRateAsDouble()) {
            return 1;
        }
        return 0;
    }
}
