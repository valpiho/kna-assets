package com.pibox.knaassets.company;

import com.pibox.knaassets.exceptions.domain.ExistException;
import com.pibox.knaassets.exceptions.domain.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public Company getCompanyById(Long id) throws NotFoundException {
        Company company = companyRepository.findCompanyById(id);
        if (company == null) {
            throw new NotFoundException("Company with ID: " + id + " was not found");
        }
        return company;
    }

    public Company getCompanyByVatNumber(String vatNumber) throws NotFoundException {
        Company company = companyRepository.findCompanyByVatNumber(vatNumber);
        if (company == null) {
            throw new NotFoundException("Company with VAT number: " + vatNumber + " was not found");
        }
        return company;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
