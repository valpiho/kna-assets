package com.pibox.knaassets.api.mapper;

import com.pibox.knaassets.api.dto.AddressDto;
import com.pibox.knaassets.company.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    Address toAddress(AddressDto addressDto);

    AddressDto toAddressDto(Address address);
}
