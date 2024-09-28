package com.restAPI.service;

import com.restAPI.dto.*;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.exception.UniqueConstraintException;
import com.restAPI.mapper.UserMapper;
import com.restAPI.model.Address;
import com.restAPI.repository.AddressRepository;
import com.restAPI.responseModel.ApiResponse;
import com.restAPI.model.User;
import com.restAPI.repository.UserRepository;
import com.restAPI.responseModel.ApiResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public ResponseEntity<ApiResponse<?>> createUser(UserRequestDTO request) throws BadRequestException, ServiceLogicException, UniqueConstraintException {

        try {
            log.info("Creating user with name: {}", request.getName());

            User user = userMapper.userRequestDTOToUser(request);
            /*user.setName(request.getName());
            user.setEmail(request.getEmail());*/
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new UniqueConstraintException("Email already Exists");
            }

            // Map addresses
          /*  List<Address> addresses = request.getAddresses().stream()
                    .map(addressRequest -> {
                        Address address = new Address();
                        address.setId(addressRequest.getId());
                        address.setCity(addressRequest.getCity());
                        address.setState(addressRequest.getState());
                        address.setCountry(addressRequest.getCountry());
                        address.setUser(user);
                        return address;
                    })
                    .collect(Collectors.toList());

            user.setAddresses(addresses);*/
           /* List<Address> addresses = new ArrayList<>();
            for (AddressRequest addressRequest : request.getAddresses()) {
                Address address = new Address();
                address.setId(addressRequest.getId());
                address.setCity(addressRequest.getCity());
                address.setState(addressRequest.getState());
                address.setCountry(addressRequest.getCountry());
                address.setUser(user);
                addresses.add(address);
            }
            user.setAddresses(addresses);*/

            // Save user and map to response DTO
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            User savedUser = userRepository.save(user);
            UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(savedUser);
            ApiResponse<UserResponseDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), ApiResponseStatus.SUCCESS.name(), "User Created Successfully..", userResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UniqueConstraintException e) {
            throw new UniqueConstraintException(e.getMessage());
        }catch (Exception ex) {
            log.error("Failed to create new user account: {}", ex.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateUser(int id,UserRequestDTO userRequestDTO) throws BadRequestException,ServiceLogicException, ResourceNotFoundException,UniqueConstraintException {
       try {
           log.info("Updating user with id: {}",id);
           User getUser=userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not existed with given id :" + id));
           userRequestDTO.setId(getUser.getId());
           User user = userMapper.userRequestDTOToUser(userRequestDTO);
          /* user.setName(userRequestDTO.getName());
           user.setEmail(userRequestDTO.getEmail());*/
           user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
           if (!userRepository.existsByEmail(user.getEmail())) {
               throw new UniqueConstraintException(user.getEmail()+"This  Email are not Exists!! first create then update user..");
           }
           // Map addresses using Stream API
          /* List<Address> addresses = userRequestDTO.getAddresses().stream()
                   .map(addressRequest -> {
                       Address address = new Address();
                       address.setId(addressRequest.getId());
                       address.setCity(addressRequest.getCity());
                       address.setState(addressRequest.getState());
                       address.setCountry(addressRequest.getCountry());
                       address.setUser(user);
                       return address;
                   })
                   .collect(Collectors.toList());

           user.setAddresses(addresses);*/
           User updateUser = userRepository.save(user);
           UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(updateUser);
           var response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "User Updated Successfully..", userResponseDTO);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }catch (ResourceNotFoundException ex){
           throw new ResourceNotFoundException(ex.getMessage());
       }catch (UniqueConstraintException ex){
           throw new UniqueConstraintException(ex.getMessage());
       }catch(Exception e) {
           log.error("Failed to update user account: {}", e.getMessage());
           throw new ServiceLogicException();
       }

    }

    @Override
    public ResponseEntity<ApiResponse<?>> getUserById(int id) throws ServiceLogicException, ResourceNotFoundException {
        try {
            log.info("Get user with id: {}",id);
            User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not existed with given id :" + id));

            UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);
            ApiResponse<UserResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "User Fetch Successfully..", userResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }catch(Exception e) {
            log.error("Failed to fetch user account: {}",e.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getUserByName(String name) throws ServiceLogicException, ResourceNotFoundException {
       try {
           log.info("Get user with Name: {}",name);
           // Fetch user by name or throw ResourceNotFoundException if not found
           User user = userRepository.findByName(name)
                   .orElseThrow(() -> new ResourceNotFoundException("User not found with name: " + name));

           // Map user to UserResponseDTO
           UserResponseDTO UserResponseDTO = userMapper.userToUserResponseDTO(user);
           ApiResponse<UserResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "User Fetch Successfully..",UserResponseDTO);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }catch (ResourceNotFoundException ex){
           throw new ResourceNotFoundException(ex.getMessage());
       }catch(Exception e) {
           log.error("Failed to fetch user account : {}",e.getMessage());
           throw new ServiceLogicException();
       }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllUser() throws ServiceLogicException, ResourceNotFoundException {
       try {
           log.info("Get all users");
           List<User> users = userRepository.findAll();
           if(users.isEmpty()){
               throw new ResourceNotFoundException("Content not Exists..");
           }
//           List<UserRequestDTO> list = users.stream()
//                   .map(userMapper::userToUserRequestDTO)
//                   .toList();

           List<UserResponseDTO> userResponseDTOS = userMapper.userListToUserResponseDTOLIst(users);
           ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "Fetching Successfully..",userResponseDTOS);
           return new ResponseEntity<>(response, HttpStatus.OK);
       } catch (ResourceNotFoundException e) {
           throw new ResourceNotFoundException(e.getMessage());
       }catch (Exception e) {
           log.error("Failed to fetch all users: {}", e.getMessage());
           throw new ServiceLogicException();
       }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getUsers() throws ServiceLogicException, ResourceNotFoundException {
        try {
            log.info("Get users");
            var users = userRepository.findAll();
            if(users.isEmpty()){
                throw new ResourceNotFoundException("Content not Exists.");
            }
//            List<UserRequestDTO> list = users.stream()
//                    .map(userMapper::userToUserRequestDTO)
//                    .toList();
            List<UserResponseDTO> userResponseDTOS = userMapper.userListToUserResponseDTOLIst(users);
            var response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "Fetching Successfully..",userResponseDTOS);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Failed to fetch users: {}", e.getMessage());
            if (e instanceof ResourceNotFoundException) {
                throw (ResourceNotFoundException) e;
            }
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> deleteUser(int id) throws ServiceLogicException, ResourceNotFoundException{
        try {
            log.info("Deleting user with id: {}",id);
            var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
            userRepository.delete(user);
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(),ApiResponseStatus.SUCCESS.name(), "User Deleted Successfully..","User Deleted...");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete user account: {}", e.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> createAddress(AddressRequest addressRequest) throws BadRequestException, ServiceLogicException {
        try {
            log.info("Creating Address with user: {}", addressRequest.getUser());

            Address address = userMapper.addressRequestDTOToAddress(addressRequest);
            // Save user and map to response DTO
            Address save = addressRepository.save(address);
            AddressResponse addressResponse = userMapper.addressToAddressResponseDTO(save);
            ApiResponse<AddressResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), ApiResponseStatus.SUCCESS.name(), "User Created Successfully..", addressResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception ex) {
            log.error("Failed to create new address : {}", ex.getMessage());
            throw new ServiceLogicException();
        }
    }
}