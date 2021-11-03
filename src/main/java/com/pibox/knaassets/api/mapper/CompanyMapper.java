package com.pibox.knaassets.api.mapper;

import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.company.Company;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {

    Company toCompany(CompanyDto companyDto);

    CompanyDto toCompanyDto(Company company);
}
