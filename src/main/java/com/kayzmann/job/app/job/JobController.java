package com.kayzmann.job.app.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable  Long id){

        Job job  =  jobService.getJobById(id);
        if(job != null)
           return new ResponseEntity<>(job, HttpStatus.OK);

        return new ResponseEntity<>((HttpStatus.NOT_FOUND));
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob( @RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED) ;
    }

}
