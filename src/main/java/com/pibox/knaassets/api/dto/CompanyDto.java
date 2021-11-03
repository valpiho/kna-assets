package com.pibox.knaassets.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pibox.knaassets.company.Address;
import com.pibox.knaassets.company.PrimaryContact;
import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.company.enums.VendorType;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyDto {

    private String title;

    private String vatNumber;

    private PrimaryContact primaryContact;

    private boolean isVendor;

    private VendorType vendorType;

    private Address shippingAddress;
    private Address billingAddress;

    @JsonFormat(timezone = "Europe/Tallinn")
    private Date createdAt;

    @JsonFormat(timezone = "Europe/Tallinn")
    private Date updatedAt;

    @JsonFormat(timezone = "Europe/Tallinn")
    private Date removedAt;

    private CompanyStatusEnum currentStatus;
}
