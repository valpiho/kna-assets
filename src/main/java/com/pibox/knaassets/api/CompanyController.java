package com.pibox.knaassets.api;

import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.api.dto.CompanyMiniDto;
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

    @PatchMapping("/{id}")
    public ResponseEntity<HttpResponse> updateCompany(@RequestBody CompanyMiniDto companyMiniDto,
                                                      @PathVariable Long id) throws NotFoundException, ExistException {
        Company updatedCompany = companyService.updateCompany(companyMapper.toCompany(companyMiniDto), id);
        return response(HttpStatus.CREATED, "A company \"" + updatedCompany.getTitle() + "\" was updated successfully.");
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
            List<CompanyMiniDto> companyMiniDtoList = companyService.getAllCompanies().stream()
                    .map(companyMapper::toCompanyMiniDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(companyMiniDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<CompanyMiniDto>> getCompaniesByTitleContains(@PathVariable String keyword) {
        List<CompanyMiniDto> companyMiniDtoList = companyService.getCompaniesByTitleContains(keyword).stream()
                .map(companyMapper::toCompanyMiniDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(companyMiniDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deactivateCompany(@PathVariable Long id) throws NotFoundException {
        String companyTitle = companyService.deactivateCompany(id);
        return response(HttpStatus.NO_CONTENT, "Company \"" + companyTitle + "\" was added successfully.\"");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, message),
                httpStatus);
    }
}
