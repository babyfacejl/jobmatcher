package com.swipejob.jobmatcher.controllers;

import com.swipejob.jobmatcher.models.Job;
import com.swipejob.jobmatcher.models.Location;
import com.swipejob.jobmatcher.models.Worker;
import com.swipejob.jobmatcher.predicate.JobPredicate;
import com.swipejob.jobmatcher.services.JobService;
import com.swipejob.jobmatcher.services.WorkerService;
import com.swipejob.jobmatcher.utils.RateComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestController.class);
    private final WorkerService workerService;
    private final JobService jobService;

    @Autowired
    public RestController(WorkerService workerService, JobService jobService) {
        this.workerService = workerService;
        this.jobService = jobService;
    }

    @GetMapping(value = "/workers/{workerId}/jobs")
    public ResponseEntity<Object> getJobsFor(@PathVariable long workerId) {
        final Optional<Worker> workerOptional = workerService.findAll().stream().filter(w -> w.getUserId() == workerId).findFirst();
        if (workerOptional.isPresent()) {
            Worker worker = workerOptional.get();
            LOGGER.debug("Worker:{}", worker);
            if (!worker.isActive()) {
                return new ResponseEntity<>("Worker not active", HttpStatus.OK);
            } else {
                List<Job> result;
                if (!worker.isHasDriversLicense()) {
                    result = jobService.findAll().stream().filter(JobPredicate.requireDriverLicense().negate()).collect(Collectors.toList());
                } else {
                    result = jobService.findAll();
                }
                List<Job> sortedResult = result.stream()
                        .filter(JobPredicate.hasCertificates(worker.getCertificates()))
                        .filter(JobPredicate.needWorkers())
                        .filter(JobPredicate.withinDistance(
                                new Location(worker.getJobSearchAddress().getLongitude(),
                                        worker.getJobSearchAddress().getLatitude()), worker.getJobSearchAddress().getMaxJobDistance()))
                        .sorted(new RateComparator())
                        .collect(Collectors.toList());
                LOGGER.debug("Matched {} jobs", sortedResult.size());
                if (CollectionUtils.isEmpty(sortedResult)) {
                    return new ResponseEntity<>("No matched jobs", HttpStatus.OK);
                }
                return new ResponseEntity<>(sortedResult.subList(0, Math.min(sortedResult.size(), 3)), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<Object>("Worker not found", HttpStatus.NOT_FOUND);
        }


    }

}
