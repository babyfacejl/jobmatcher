package com.swipejob.jobmatcher.predicate;

import com.swipejob.jobmatcher.utils.DistanceHelper;
import com.swipejob.jobmatcher.models.Job;
import com.swipejob.jobmatcher.models.Location;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JobPredicate
{
    public static Predicate<Job> requireDriverLicense(boolean required) {
        return p -> p.isDriverLicenseRequired() == required;
    }

    public static Predicate<Job> hasCertificates(List<String> certificates) {
        return p -> certificates.containsAll(p.getRequiredCertificates());
    }

    public static Predicate<Job> needWorkers() {
        return p -> p.getWorkersRequired() > 0;
    }

    public static Predicate<Job> withinDistance(Location location, long distance) {
//        System.out.println(" = " + (p -> DistanceHelper.distance(p.getLocation(), location) <= distance
        return p -> DistanceHelper.distance(p.getLocation(), location) <= 30;
    }

    public static List<Job> filterJobs (List<Job> jobs,
                                                  Predicate<Job> predicate)
    {
        return jobs.stream()
                .filter(predicate)
                .collect(Collectors.<Job>toList());
    }
}
