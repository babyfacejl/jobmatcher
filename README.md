# Job matcher service for worker

Returned the first 3 matched jobs for a worker

Matching logic
- Worker must be active
- If worker has no drivers license then filter out jobs that require drivers license
- Worker has all the certificates the job requires
- Job has at least 1 worker needed
- Job location is within worker's job search address range. (User Harversine formula to calculate the distance between 2 points on earth. Refer to https://www.movable-type.co.uk/scripts/latlong.html)

Finally sorted jobs by highest paid rate job to lowest paid rate job

## Prerequisites

* Java 11
* Spring boot
* Maven

## Domain model object

* Worker

* Job


## API Endpoints
* /api/workers/{workerId}/jobs 
```
 returns first 3 matched jobs for the worker
 Can use postman to do GET
 e.g http://localhost:8080/api/workers/8/jobs
```

## Getting Started

Git clone the repo
```
$ git clone https://github.com/babyfacejl/jobmatcher.git
```

## Start the service

Run following command to start the service
```
$ mvn spring-boot:run
```

## Installing

Run maven command to generate jar file
```
$ mvn clean install 
```
## Running the tests

$ mvn test

## Authors
* **John Luo** 



