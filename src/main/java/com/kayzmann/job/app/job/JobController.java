package com.kayzmann.job.app.job;

import com.kayzmann.job.app.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable  Long id){

        Job job  =  jobService.getJobById(id);
        if(job != null)
           return new ResponseEntity<>(job, HttpStatus.OK);

        return new ResponseEntity<>((HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createJob( @RequestBody Job job){
        jobService.createJob(job);
        Company c  =  job.getCompany();
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted =  jobService.deleteJobById(id);
        if(deleted)

        return new ResponseEntity<>("Job deleted Successfully", HttpStatus.OK);

        return null;
    }


    @PutMapping("/{id}")
//    another method @RequestMapping(value = "/jobs/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated  =  jobService.updateJob(id, updatedJob);

        if(updated)
            return new  ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
