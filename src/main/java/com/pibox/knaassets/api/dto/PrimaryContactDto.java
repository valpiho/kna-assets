package com.pibox.knaassets.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrimaryContactDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;
}
