package com.kayzmann.job.app.company;

import com.kayzmann.job.app.job.Job;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;

    //every company has a list of jobs
    @OneToMany
    private List<Job> jobs;

    //Reviews mapping afterwards

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void set() {

    }
}
