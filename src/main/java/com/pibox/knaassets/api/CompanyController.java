package com.pibox.knaassets.api;

import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.api.mapper.CompanyMapper;
import com.pibox.knaassets.company.Company;
import com.pibox.knaassets.company.CompanyService;
import com.pibox.knaassets.api.models.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public CompanyController(CompanyService companyService,
                             CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping
    // TODO: Available for managers
    public ResponseEntity<HttpResponse> addCompany(@RequestBody CompanyDto companyDto) {
        Company newCompany = companyService.addCompany(companyMapper.toCompany(companyDto));
        LOGGER.info("A new company was added successfully.");
        return response(HttpStatus.OK, "A new company \"" + companyDto.getTitle() + "\" was added successfully.");
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, message),
                httpStatus);
    }
}
