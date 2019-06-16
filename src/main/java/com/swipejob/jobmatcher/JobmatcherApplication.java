package com.swipejob.jobmatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JobmatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobmatcherApplication.class, args);
    }

}
