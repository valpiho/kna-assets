package com.pibox.knaassets.api;

import com.pibox.knaassets.company.CompanyService;
import com.pibox.knaassets.api.models.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, message),
                httpStatus);
    }
}
