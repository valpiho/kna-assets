package com.pibox.knaassets.company;

import com.pibox.knaassets.exceptions.domain.ExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company addCompany(Company company) throws ExistException {
        Company newCompany = new Company();
        newCompany.setTitle(company.getTitle());
        if (companyRepository.findCompanyByVatNumber(company.getVatNumber()) != null) {
            LOGGER.warn("Company was not created.");
            throw new ExistException("Company with VAT number: " + company.getVatNumber() + " is already exist");
        }
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
        LOGGER.info("A new company was added successfully.");
        return newCompany;
    }
}
