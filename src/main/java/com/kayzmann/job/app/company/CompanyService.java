package com.kayzmann.job.app.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);

}
