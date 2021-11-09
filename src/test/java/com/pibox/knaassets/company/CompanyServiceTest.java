package com.pibox.knaassets.company;

import com.pibox.knaassets.api.dto.AddressDto;
import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.api.dto.PrimaryContactDto;
import com.pibox.knaassets.api.mapper.CompanyMapper;
import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.company.enums.VendorType;
import com.pibox.knaassets.exceptions.domain.ExistException;
import com.pibox.knaassets.exceptions.domain.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        companyService.addCompany(company);
        ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository).save(companyArgumentCaptor.capture());
        Company capturedCompany = companyArgumentCaptor.getValue();

        assertThat(capturedCompany)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(company);
        assertThat(capturedCompany.getCreatedAt()).isNotNull();
    }

    @Test
    void addCompanyWillThrowExistException() {

        Company company = companyMapper.toCompany(companyDto);
        given(companyRepository.findCompanyByVatNumber(anyString())).willReturn(company);

        assertThatThrownBy(() -> companyService.addCompany(company))
                .isInstanceOf(ExistException.class)
                .hasMessageContaining("Company with VAT number: " + company.getVatNumber() + " is already exist");

        verify(companyRepository, never()).save(any());
    }

    @Test
    void canGetCompanyById() throws NotFoundException {

        Company company = companyMapper.toCompany(companyDto);
        given(companyRepository.findCompanyById(anyLong())).willReturn(company);

        Company expectedCompany = companyService.getCompanyById(1L);

        verify(companyRepository).findCompanyById(1L);
        assertThat(expectedCompany).isEqualTo(company);
    }

    @Test
    void getCompanyByIdWillThrowNotFoundException() {

        given(companyRepository.findCompanyById(anyLong())).willReturn(null);

        assertThatThrownBy(() -> companyService.getCompanyById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Company with ID: " + 1 + " was not found");
    }

    @Test
    void canGetCompanyByVatNumber() throws NotFoundException {

        Company company = companyMapper.toCompany(companyDto);
        given(companyRepository.findCompanyByVatNumber(anyString())).willReturn(company);

        Company expectedCompany = companyService.getCompanyByVatNumber("EE12345678");

        verify(companyRepository).findCompanyByVatNumber("EE12345678");
        assertThat(expectedCompany).isEqualTo(company);
    }

    @Test
    void getCompanyByVatNumberWillThrowNotFoundException() {

        given(companyRepository.findCompanyByVatNumber(anyString())).willReturn(null);

        assertThatThrownBy(() -> companyService.getCompanyByVatNumber("EE12345678"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Company with VAT number: EE12345678 was not found");
    }

    @Test
    void canGetAllCompanies() {

        companyService.getAllCompanies();

        verify(companyRepository).findAll();
    }

    @Test
    void canGetCompaniesByTitleContains() {

        Company company = companyMapper.toCompany(companyDto);
        List<Company> companyList = new ArrayList<>();
        companyList.add(company);
        when(companyRepository.findCompaniesByTitleContains(anyString())).thenReturn(companyList);

        List<Company> expectedCompanyList = companyService.getCompaniesByTitleContains("pibox");

        verify(companyRepository).findCompaniesByTitleContains("pibox");
        assertThat(expectedCompanyList.size()).isEqualTo(1);
        assertThat(expectedCompanyList.get(0)).isEqualTo(company);
    }
}