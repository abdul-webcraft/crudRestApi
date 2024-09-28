package com.restAPI.mapper;

import com.restAPI.dto.AddressRequest;
import com.restAPI.dto.AddressResponse;
import com.restAPI.dto.UserRequestDTO;
import com.restAPI.dto.UserResponseDTO;
import com.restAPI.model.Address;
import com.restAPI.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T10:10:45+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO userToUserResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.id( user.getId() );
        userResponseDTO.name( user.getName() );
        userResponseDTO.email( user.getEmail() );
        userResponseDTO.password( user.getPassword() );
        userResponseDTO.addresses( addressListToAddressResponseList( user.getAddresses() ) );

        return userResponseDTO.build();
    }

    @Override
    public User userRequestDTOToUser(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userRequestDTO.getId() );
        user.setName( userRequestDTO.getName() );
        user.setEmail( userRequestDTO.getEmail() );
        user.setPassword( userRequestDTO.getPassword() );
        user.setAddresses( addressRequestListToAddressList( userRequestDTO.getAddresses() ) );

        setAddress( user );

        return user;
    }

    @Override
    public List<UserResponseDTO> userListToUserResponseDTOLIst(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponseDTO( user ) );
        }

        return list;
    }

    @Override
    public User userResponseDTOToUser(UserResponseDTO userResponseDTO) {
        if ( userResponseDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userResponseDTO.getId() );
        user.setName( userResponseDTO.getName() );
        user.setEmail( userResponseDTO.getEmail() );
        user.setPassword( userResponseDTO.getPassword() );
        user.setAddresses( addressResponseListToAddressList( userResponseDTO.getAddresses() ) );

        setAddress( user );

        return user;
    }

    @Override
    public Address addressRequestDTOToAddress(AddressRequest addressRequest) {
        if ( addressRequest == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressRequest.getId() );
        address.setCity( addressRequest.getCity() );
        address.setState( addressRequest.getState() );
        address.setCountry( addressRequest.getCountry() );
        address.setUser( userRequestDTOToUser( addressRequest.getUser() ) );

        return address;
    }

    @Override
    public AddressResponse addressToAddressResponseDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponse.AddressResponseBuilder addressResponse = AddressResponse.builder();

        addressResponse.id( address.getId() );
        addressResponse.city( address.getCity() );
        addressResponse.state( address.getState() );
        addressResponse.country( address.getCountry() );

        return addressResponse.build();
    }

    @Override
    public Address addressRequestDTOtoAddress(AddressRequest addresses) {
        if ( addresses == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addresses.getId() );
        address.setCity( addresses.getCity() );
        address.setState( addresses.getState() );
        address.setCountry( addresses.getCountry() );
        address.setUser( userRequestDTOToUser( addresses.getUser() ) );

        return address;
    }

    @Override
    public List<Address> addressRequestDTOListToAddressList(List<AddressRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressRequest addressRequest : list ) {
            list1.add( addressRequestDTOtoAddress( addressRequest ) );
        }

        return list1;
    }

    protected List<AddressResponse> addressListToAddressResponseList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressResponse> list1 = new ArrayList<AddressResponse>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressResponseDTO( address ) );
        }

        return list1;
    }

    protected List<Address> addressRequestListToAddressList(List<AddressRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressRequest addressRequest : list ) {
            list1.add( addressRequestDTOToAddress( addressRequest ) );
        }

        return list1;
    }

    protected Address addressResponseToAddress(AddressResponse addressResponse) {
        if ( addressResponse == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressResponse.getId() );
        address.setCity( addressResponse.getCity() );
        address.setState( addressResponse.getState() );
        address.setCountry( addressResponse.getCountry() );

        return address;
    }

    protected List<Address> addressResponseListToAddressList(List<AddressResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressResponse addressResponse : list ) {
            list1.add( addressResponseToAddress( addressResponse ) );
        }

        return list1;
    }
}
