package com.restAPI.mapper;

import com.restAPI.dto.AddressRequest;
import com.restAPI.dto.AddressResponse;
import com.restAPI.dto.UserRequestDTO;
import com.restAPI.dto.UserResponseDTO;
import com.restAPI.model.Address;
import com.restAPI.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

     UserResponseDTO userToUserResponseDTO(User user);

//    @Mapping(target = "addresses", qualifiedByName = "addressRequestDTOListToAddressList")
    User userRequestDTOToUser(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> userListToUserResponseDTOLIst(List<User> users);


    User userResponseDTOToUser(UserResponseDTO userResponseDTO);

    Address addressRequestDTOToAddress(AddressRequest addressRequest);

    AddressResponse addressToAddressResponseDTO(Address address);

    @Named("addressRequestDTOtoAddress")
    Address addressRequestDTOtoAddress(AddressRequest addresses);

    @IterableMapping(qualifiedByName = "addressRequestDTOtoAddress")
    @Named("addressRequestDTOListToAddressList")
    List<Address> addressRequestDTOListToAddressList(List<AddressRequest> list);

    @AfterMapping
    default void setAddress(@MappingTarget User user) {

        Optional.ofNullable(user.getAddresses())
                .ifPresent(it -> it.forEach(item -> item.setUser(user)));
    }


}
