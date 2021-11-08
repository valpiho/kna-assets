package com.pibox.knaassets.company;

import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.exceptions.domain.ExistException;
import com.pibox.knaassets.exceptions.domain.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company addCompany(Company company) throws ExistException {
        Company companyToAdd = new Company();
        companyToAdd.setTitle(company.getTitle());
        if (companyRepository.findCompanyByVatNumber(company.getVatNumber()) != null) {
            throw new ExistException("Company with VAT number: " + company.getVatNumber() + " is already exist");
        }
        companyToAdd.setVatNumber(company.getVatNumber());
        companyToAdd.setPrimaryContact(company.getPrimaryContact());
        if (company.isVendor()) {
            companyToAdd.setVendor(true);
            companyToAdd.setVendorType(company.getVendorType());
        }
        companyToAdd.setShippingAddress(company.getShippingAddress());
        companyToAdd.setBillingAddress(company.getBillingAddress());
        companyToAdd.setCurrentStatus(company.getCurrentStatus());
        companyToAdd.setCreatedAt(new Date());
        Company newCompany = companyRepository.save(companyToAdd);
        LOGGER.info("A new company with ID: '" + newCompany.getId() + "' was added successfully.");
        return newCompany;
    }

    public Company updateCompany(Company company, Long id) throws NotFoundException, ExistException {
        Company companyToUpdate = companyRepository.findCompanyById(id);
        if (companyToUpdate == null) {
            throw new NotFoundException("Company with ID: " + id + " was not found");
        }
        companyToUpdate.setTitle(company.getTitle());
        if (companyRepository.findCompanyByVatNumber(company.getVatNumber()) != null &&
                !Objects.equals(company.getVatNumber(), companyToUpdate.getVatNumber())) {
            throw new ExistException("Company with VAT number: " + company.getVatNumber() + " is already exist");
        }
        companyToUpdate.setVatNumber(company.getVatNumber());
        companyToUpdate.setPrimaryContact(company.getPrimaryContact());
        if (company.isVendor()) {
            companyToUpdate.setVendor(true);
            companyToUpdate.setVendorType(company.getVendorType());
        }
        companyToUpdate.setShippingAddress(company.getShippingAddress());
        companyToUpdate.setBillingAddress(company.getBillingAddress());
        companyToUpdate.setCurrentStatus(company.getCurrentStatus());
        companyToUpdate.setUpdatedAt(new Date());
        Company updatedCompany = companyRepository.save(companyToUpdate);
        LOGGER.info("A company with ID: '" + updatedCompany.getId() + "' was updated successfully.");
        return updatedCompany;
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

    public String deactivateCompany(Long id) throws NotFoundException {
        Company company = companyRepository.findCompanyById(id);
        if (company == null) {
            throw new NotFoundException("Company with ID: " + id + " was not found");
        }
        company.setCurrentStatus(CompanyStatusEnum.NOT_AVAILABLE);
        company.setRemovedAt(new Date());
        companyRepository.save(company);
        LOGGER.info("A company with ID: '" + company.getId() + "' was achieved successfully.");
        return company.getTitle();
    }
}
