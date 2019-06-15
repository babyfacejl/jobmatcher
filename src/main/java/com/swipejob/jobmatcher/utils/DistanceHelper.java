package com.swipejob.jobmatcher.utils;

import com.swipejob.jobmatcher.models.Location;

public final class DistanceHelper {
    private static final int EARTH_RADIUS = 6371; // kilometres

    public static long distance(Location location1, Location location2) {
        double q1 = Math.toRadians(location1.getLatitude());
        double q2 = Math.toRadians(location2.getLatitude());
        double deltaQ = Math.toRadians(location2.getLatitude() - location1.getLatitude());
        double deltaL = Math.toRadians(location2.getLongitude() - location1.getLongitude());

        double a = Math.sin(deltaQ / 2) * Math.sin(deltaQ / 2) +
                Math.cos(q1) * Math.cos(q2) *
                        Math.sin(deltaL / 2) * Math.sin(deltaL / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        System.out.println("(long) (c * EARTH_RADIUS) = " + (long) (c * EARTH_RADIUS));
        return (long) (c * EARTH_RADIUS);
    }
}
