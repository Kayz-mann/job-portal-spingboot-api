package com.kayzmann.job.app.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PutMapping
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompany(company, id);
        return new ResponseEntity<>("Company upated successfully", HttpStatus.OK);
    }
}
