package com.pibox.knaassets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.company.enums.VendorType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CompanyMiniDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String vatNumber;

    private PrimaryContactDto primaryContact;

    private boolean isVendor;

    private VendorType vendorType;

    private AddressDto shippingAddress;

    private AddressDto billingAddress;

    private CompanyStatusEnum currentStatus;
}
