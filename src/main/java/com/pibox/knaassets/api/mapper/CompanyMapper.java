package com.pibox.knaassets.api.mapper;

import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.api.dto.CompanyMiniDto;
import com.pibox.knaassets.company.Company;
import org.mapstruct.Mapper;

@Mapper(uses = {PrimaryContactMapper.class, AddressMapper.class})
public interface CompanyMapper {

    Company toCompany(CompanyDto companyDto);
    Company toCompany(CompanyMiniDto companyMiniDto);

    CompanyDto toCompanyDto(Company company);

    CompanyMiniDto toCompanyMiniDto(Company company);
}
