package com.pibox.knaassets.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.company.enums.VendorType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CompanyDto {

    private String title;

    private String vatNumber;

    private PrimaryContactDto primaryContact;

    private boolean isVendor;

    private VendorType vendorType;

    private AddressDto shippingAddress;

    private AddressDto billingAddress;

    @JsonFormat(timezone = "Europe/Tallinn")
    private Date createdAt;

    @JsonFormat(timezone = "Europe/Tallinn")
    private Date updatedAt;

    @JsonFormat(timezone = "Europe/Tallinn")
    private Date removedAt;

    private CompanyStatusEnum currentStatus;
}
