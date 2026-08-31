package com.kayzmann.jobms.job;

import com.kayzmann.jobms.job.dto.JobDTO;
import com.kayzmann.jobms.job.dto.JobRequest;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(JobRequest jobRequest);
    JobDTO getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id, JobRequest jobRequest);
}
