package com.kayzmann.job.app.job;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private JobService jobService;

    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobService.findAll();
    }

    @PostMapping("/jobs")
    public String createJob( @RequestBody Job job){
        jobService.createJob(job);
        return "Job added successfully";
    }
}
