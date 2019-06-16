package com.swipejob.jobmatcher.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.swipejob.jobmatcher.models.Job;
import com.swipejob.jobmatcher.models.Location;
import com.swipejob.jobmatcher.models.Worker;
import com.swipejob.jobmatcher.predicate.JobPredicate;
import com.swipejob.jobmatcher.utils.RateComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Value("${swipejob.workers.url}")
    private String workersUrl;
    @Value("${swipejob.jobs.url}")
    private String jobsUrl;
    private static List<Job> jobs;
    private static Map<Long, Worker> workerMap;
    private final static Logger LOGGER = LoggerFactory.getLogger(RestController.class);

    @GetMapping(value = "/workers/{workerId}/jobs")
    public ResponseEntity<List<Job>> getJobsFor(@PathVariable long workerId) {
        final Worker worker = workerMap.get(workerId);
        LOGGER.debug("Worker=", worker);
        if (!worker.isActive()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            List<Job> result;
            if (!worker.isHasDriversLicense()) {
                result = jobs.stream().filter(JobPredicate.requireDriverLicense().negate()).collect(Collectors.toList());
            } else {
                result = jobs;
            }
            List<Job> sortedResult = result.stream()
                    .filter(JobPredicate.hasCertificates(worker.getCertificates()))
                    .filter(JobPredicate.needWorkers())
                    .filter(JobPredicate.withinDistance(
                            new Location(worker.getJobSearchAddress().getLongitude(),
                                    worker.getJobSearchAddress().getLatitude()), worker.getJobSearchAddress().getMaxJobDistance()))
                    .sorted(new RateComparator())
                    .collect(Collectors.toList());
            LOGGER.debug("Matched job =", sortedResult.size());
            return new ResponseEntity<>(sortedResult.subList(0, Math.min(sortedResult.size(), 3)), HttpStatus.OK);
        }
    }

    @PostConstruct
    public void init() {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        try {
            String jobsJson = restTemplate.getForObject(jobsUrl, String.class);
            String workersJson = restTemplate.getForObject(workersUrl, String.class);
            jobs = objectMapper.readValue(jobsJson, new TypeReference<List<Job>>() {});
            List<Worker> workers = objectMapper.readValue(workersJson, new TypeReference<List<Worker>>() {});
            workerMap = workers.stream().collect(Collectors.toMap(Worker::getUserId, Function.identity()));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }

}
