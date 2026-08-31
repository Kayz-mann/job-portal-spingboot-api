package com.kayzmann.jobms.job;

import com.kayzmann.jobms.job.dto.JobDTO;
import com.kayzmann.jobms.job.dto.JobRequest;
import com.kayzmann.jobms.job.external.Company;
import com.kayzmann.jobms.job.external.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobMapper {

    public Job toEntity(JobRequest request) {
        Job job = new Job();
        applyRequest(job, request);
        return job;
    }

    public void updateEntity(Job job, JobRequest request) {
        applyRequest(job, request);
    }

    public JobDTO toDto(Job job, Company company, List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompanyId(job.getCompanyId());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }

    private void applyRequest(Job job, JobRequest request) {
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());
        job.setLocation(request.getLocation());
        job.setCompanyId(request.getCompanyId());
    }
}
