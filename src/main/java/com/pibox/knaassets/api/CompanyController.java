package com.pibox.knaassets.api;

import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.api.mapper.CompanyMapper;
import com.pibox.knaassets.company.Company;
import com.pibox.knaassets.company.CompanyService;
import com.pibox.knaassets.api.models.HttpResponse;
import com.pibox.knaassets.exceptions.domain.ExistException;
import com.pibox.knaassets.exceptions.domain.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService,
                             CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> addCompany(@RequestBody CompanyDto companyDto) throws ExistException {
        Company newCompany = companyService.addCompany(companyMapper.toCompany(companyDto));
        return response(HttpStatus.CREATED, "A new company \"" + newCompany.getTitle() + "\" was added successfully.");
    }

    @GetMapping()
    public ResponseEntity<?> getCompanies(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String vatNumber) throws NotFoundException {
        CompanyDto companyDto = null;

        if (id != null) {
            companyDto = companyMapper.toCompanyDto(companyService.getCompanyById(id));
            return new ResponseEntity<>(companyDto, HttpStatus.OK);
        }  else if (vatNumber != null) {
            companyDto = companyMapper.toCompanyDto(companyService.getCompanyByVatNumber(vatNumber));
            return new ResponseEntity<>(companyDto, HttpStatus.OK);
        } else {
            List<CompanyDto> companyDtoList = companyService.getAllCompanies().stream()
                    .map(companyMapper::toCompanyDto).collect(Collectors.toList());
            return new ResponseEntity<>(companyDtoList, HttpStatus.OK);
        }
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, message),
                httpStatus);
    }
}
