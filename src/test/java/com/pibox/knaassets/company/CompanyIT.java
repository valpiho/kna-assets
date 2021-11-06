package com.pibox.knaassets.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pibox.knaassets.api.dto.AddressDto;
import com.pibox.knaassets.api.dto.CompanyDto;
import com.pibox.knaassets.api.dto.PrimaryContactDto;
import com.pibox.knaassets.api.mapper.CompanyMapper;
import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.company.enums.VendorType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class CompanyIT {

    @MockBean
    private CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CompanyMapper companyMapper;

    @Test
    void addCompany() throws Exception {

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

        doReturn(companyMapper.toCompany(companyDto)).when(companyService).addCompany(any());


        mockMvc.perform(post("/api/v1/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(companyDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("A new company \"Pibox OU\" was added successfully.")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
