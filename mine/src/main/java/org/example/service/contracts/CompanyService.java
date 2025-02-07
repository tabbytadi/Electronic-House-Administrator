package org.example.service.contracts;

import org.example.dto.CompanyDto;
import org.example.entity.Company;

import java.util.List;

public interface CompanyService {
    void createCompany(CompanyDto companyDto);
    void updateCompany(CompanyDto companyDto);
    void deleteCompany(long companyId);
    Company getCompanyById(long companyId);
    List<Company> getAllCompanies();
}