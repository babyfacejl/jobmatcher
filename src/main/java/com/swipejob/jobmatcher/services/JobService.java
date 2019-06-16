package com.swipejob.jobmatcher.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.swipejob.jobmatcher.models.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"jobs"})
public class JobService {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobService.class);
    @Value("${swipejob.jobs.url}")
    private String jobsUrl;
    private List<Job> jobs = new ArrayList<>();

    @Cacheable // caches the result of findAll() method
    public List<Job> findAll() {
        return this.jobs;
    }

    @PostConstruct
    private void putJob() {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        try {
            String jobsJson = restTemplate.getForObject(jobsUrl, String.class);
            jobs = objectMapper.readValue(jobsJson, new TypeReference<List<Job>>() {
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
