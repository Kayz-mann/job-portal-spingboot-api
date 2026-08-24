package com.kayzmann.jobms.job.implementation;

import com.kayzmann.jobms.job.Job;
import com.kayzmann.jobms.job.JobRepository;
import com.kayzmann.jobms.job.JobService;
import com.kayzmann.jobms.job.dto.JobDTO;
import com.kayzmann.jobms.job.external.Company;
import com.kayzmann.jobms.job.external.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final RestTemplate restTemplate;

    @Value("${company.service.url:http://localhost:8081}")
    private String companyServiceUrl;

    @Value("${review.service.url:http://localhost:8082}")
    private String reviewServiceUrl;

    public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOs.add(convertToDto(job));
        }
        return jobDTOs;
    }

    private JobDTO convertToDto(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJob(job);

        if (job.getCompanyId() != null) {
            try {
                Company company = restTemplate.getForObject(
                        companyServiceUrl + "/companies/" + job.getCompanyId(),
                        Company.class
                );
                jobDTO.setCompany(company);
            } catch (Exception e) {
                // Handle graceful fallback if company service is temporarily unavailable
                jobDTO.setCompany(null);
            }

            try {
                ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                        reviewServiceUrl + "/reviews?companyId=" + job.getCompanyId(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Review>>() {}
                );
                jobDTO.setReviews(reviewResponse.getBody());
            } catch (Exception e) {
                // Handle graceful fallback if review service is temporarily unavailable
                jobDTO.setReviews(new ArrayList<>());
            }
        }
        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            return convertToDto(job);
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            if (jobRepository.existsById(id)) {
                jobRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            job.setCompanyId(updatedJob.getCompanyId());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
