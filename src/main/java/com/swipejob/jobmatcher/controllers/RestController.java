package com.swipejob.jobmatcher.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.swipejob.jobmatcher.models.Job;
import com.swipejob.jobmatcher.models.Location;
import com.swipejob.jobmatcher.models.Worker;
import com.swipejob.jobmatcher.predicate.JobPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
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

//    @GetMapping(value = "/worker/{workerId}")
//    public ResponseEntity<Worker> getWorker(@PathVariable long workerId) {
//        Worker result = workerMap.get(workerId);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping(value = "/worker/{workerId}/jobs")
    public ResponseEntity<List<Job>> getJobsFor(@PathVariable long workerId) {
        Worker worker = workerMap.get(workerId);
        System.out.println("worker = " + worker);
        if (!worker.isActive()){
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            List<Job> result = jobs.stream()
                    .filter(JobPredicate.requireDriverLicense(worker.isHasDriversLicense()))
                            .filter(JobPredicate.hasCertificates(worker.getCertificates()))
                            .filter(JobPredicate.needWorkers())
                            .filter(JobPredicate.withinDistance(
                                    new Location(worker.getJobSearchAddress().getLongitude(),
                                            worker.getJobSearchAddress().getLatitude()), worker.getJobSearchAddress().getMaxJobDistance()))
                    .collect(Collectors.toList());
            result.stream().forEach(System.out::println);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }


    @PostConstruct
    public void init(){
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        String jobsJson = restTemplate.getForObject(jobsUrl, String.class);
        String workersJson = restTemplate.getForObject(workersUrl, String.class);
        try {
            this.jobs = objectMapper.readValue(jobsJson, new TypeReference<List<Job>>(){});

            List<Worker> workers = objectMapper.readValue(workersJson, new TypeReference<List<Worker>>(){});
            this.workerMap =
                    workers.stream().collect(Collectors.toMap(Worker::getUserId, Function.identity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
