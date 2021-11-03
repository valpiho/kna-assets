package com.pibox.knaassets.company;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public Company addCompany(Company company) {
        Company newCompany = new Company();
        newCompany.setTitle(company.getTitle());
        newCompany.setVatNumber(company.getVatNumber());
        newCompany.setPrimaryContact(company.getPrimaryContact());
        if (company.isVendor()) {
            newCompany.setVendor(true);
            newCompany.setVendorType(company.getVendorType());
        }
        newCompany.setShippingAddress(company.getShippingAddress());
        newCompany.setBillingAddress(company.getBillingAddress());
        newCompany.setCreatedAt(new Date());
        newCompany.setCurrentStatus(company.getCurrentStatus());
        companyRepository.save(newCompany);
        return newCompany;
    }
}
