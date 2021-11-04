package com.pibox.knaassets.api.mapper;

import com.pibox.knaassets.api.dto.PrimaryContactDto;
import com.pibox.knaassets.company.PrimaryContact;
import org.mapstruct.Mapper;

@Mapper
public interface PrimaryContactMapper {

    PrimaryContact toPrimaryContact(PrimaryContactDto primaryContactDto);

    PrimaryContactDto toPrimaryContactDto(PrimaryContact primaryContact);
}
