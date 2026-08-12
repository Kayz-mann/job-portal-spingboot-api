package com.kayzmann.job.app.company.implementation;

import com.kayzmann.job.app.company.Company;


import com.kayzmann.job.app.company.CompanyRepository;
import com.kayzmann.job.app.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
