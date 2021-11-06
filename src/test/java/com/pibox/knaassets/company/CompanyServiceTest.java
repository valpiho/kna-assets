package com.pibox.knaassets.company;

import com.pibox.knaassets.api.dto.AddressDto;
import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.api.dto.PrimaryContactDto;
import com.pibox.knaassets.api.mapper.CompanyMapper;
import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.company.enums.VendorType;
import com.pibox.knaassets.exceptions.domain.ExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @Autowired
    CompanyMapper companyMapper;

    private CompanyService companyService;

    CompanyDto companyDto = CompanyDto.builder()
            .title("Pibox OU")
            .vatNumber("EE12345678")
            .primaryContact(new PrimaryContactDto("Valentin", "Piho", "1234567890", "test@gmail.com"))
            .isVendor(true)
            .vendorType(VendorType.MANUFACTURE)
            .shippingAddress(new AddressDto("Liivalaia 13", "", "Tallinn",
                    "Estonia", "Harjumaa", "31333", "1234567890", "test@gmail.com"))
            .billingAddress(new AddressDto("Rae 7", "", "Tallinn",
                    "Estonia", "Harjumaa", "31433", "1234567890", "test@gmail.com"))
            .currentStatus(CompanyStatusEnum.PENDING_APPROVAL)
            .build();


    @BeforeEach
    void setUp() {
        companyService = new CompanyService(companyRepository);
    }

    @Test
    void canAddCompany() throws ExistException {

        Company company = companyMapper.toCompany(companyDto);
        companyService.addCompany(company);
        ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository).save(companyArgumentCaptor.capture());
        Company capturedCompany = companyArgumentCaptor.getValue();

        assertThat(capturedCompany)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(company);
    }

    @Test
    void willThrowExistError() {

        Company company = companyMapper.toCompany(companyDto);
        given(companyRepository.findCompanyByVatNumber(anyString())).willReturn(company);

        assertThatThrownBy(() -> companyService.addCompany(company))
                .isInstanceOf(ExistException.class)
                .hasMessageContaining("Company with VAT number: " + company.getVatNumber() + " is already exist");

        verify(companyRepository, never()).save(any());
    }
}