package com.restAPI.mapper;

import com.restAPI.dto.AddressRequest;
import com.restAPI.dto.AddressResponse;
import com.restAPI.dto.UserRequestDTO;
import com.restAPI.dto.UserResponseDTO;
import com.restAPI.model.Address;
import com.restAPI.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);


    AddressRequest AddressToAddressRequestDTO(Address address);

    Address addressRequestDTOToAddress(AddressRequest addressRequest);

    AddressResponse addressToAddressResponseDTO(Address address);

}
