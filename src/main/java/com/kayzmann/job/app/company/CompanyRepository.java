package com.kayzmann.job.app.company;

import com.kayzmann.job.app.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
