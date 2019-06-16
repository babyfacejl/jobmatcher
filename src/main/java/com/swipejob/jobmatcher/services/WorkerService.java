package com.swipejob.jobmatcher.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.swipejob.jobmatcher.models.Worker;
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
@CacheConfig(cacheNames = {"workers"})
public class WorkerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WorkerService.class);
    @Value("${swipejob.workers.url}")
    private String workersUrl;
    private List<Worker> workers = new ArrayList<>();

    @Cacheable // caches the result of findAll() method
    public List<Worker> findAll() {
        return this.workers;
    }
    @PostConstruct
    private void putWorker() {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        try {
            String workersJson = restTemplate.getForObject(workersUrl, String.class);
            workers = objectMapper.readValue(workersJson, new TypeReference<List<Worker>>() {
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
