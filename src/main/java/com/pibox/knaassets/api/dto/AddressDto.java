package com.pibox.knaassets.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {

    private String streetAddressFirstLine;

    private String streetAddressSecondLine;

    private String city;

    private String country;

    private String state;

    private String zipCode;

    private String phoneNumber;

    private String email;
}
