package com.restAPI.mapper;

import com.restAPI.dto.AddressRequest;
import com.restAPI.dto.AddressResponse;
import com.restAPI.model.Address;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T09:51:11+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressRequest AddressToAddressRequestDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressRequest addressRequest = new AddressRequest();

        return addressRequest;
    }

    @Override
    public Address addressRequestDTOToAddress(AddressRequest addressRequest) {
        if ( addressRequest == null ) {
            return null;
        }

        Address address = new Address();

        return address;
    }

    @Override
    public AddressResponse addressToAddressResponseDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponse addressResponse = new AddressResponse();

        return addressResponse;
    }
}
