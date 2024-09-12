package com.restAPI.service;

import com.restAPI.dto.UserRequest;
import com.restAPI.dto.UserResponse;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.responseModel.ApiResponse;
import com.restAPI.model.User;
import com.restAPI.repository.UserRepository;
import com.restAPI.responseModel.ApiResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Component
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<ApiResponse<?>> createUser(UserRequest request)throws BadRequestException, ServiceLogicException {

        try {
            log.info("Creating user with name: {}", request.getName());
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setAddress(request.getAddress());
            User saveUser = userRepository.save(user);

            UserResponse userResponse = new UserResponse(saveUser.getId(), saveUser.getName(), saveUser.getEmail(), saveUser.getPassword(), saveUser.getAddress());
            ApiResponse<UserResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), ApiResponseStatus.SUCCESS.name(), "User Created Successfully..", userResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception ex) {
            log.error("Failed to create new user account: {}", ex.getMessage());
            throw new ServiceLogicException();
            /*UserResponse userResponse = new UserResponse(request.getId(), request.getName(), request.getEmail(),request.getPassword(),request.getAddress());
            ApiResponse<UserResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), "User Created Successfully..", userResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);*/
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateUser(int id,UserRequest request) throws BadRequestException,ServiceLogicException, ResourceNotFoundException {
       try {
           log.info("Updating user with id: {}", request.getId());
           User user=userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not existed with given id :" + id));
           user.setName(request.getName());
           user.setEmail(request.getEmail());
           user.setPassword(request.getPassword());
           user.setAddress(request.getAddress());
           User updateUser=userRepository.save(user);
           UserResponse userResponse = new UserResponse(updateUser.getId(), updateUser.getName(), updateUser.getEmail(), updateUser.getPassword(), updateUser.getAddress());
           ApiResponse<UserResponse> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "User Updated Successfully..", userResponse);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }catch (ResourceNotFoundException ex){
           throw new ResourceNotFoundException(ex.getMessage());
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
            UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAddress());
            ApiResponse<UserResponse> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "User Fetch Successfully..", userResponse);
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
           User user = userRepository.findByName(name);
           if(user==null){
               throw new ResourceNotFoundException("User not existed with given name :" + name);
           }
           UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAddress());
           ApiResponse<UserResponse> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "User Fetch Successfully..", userResponse);
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
          /* List<UserDTO> list = users.stream()
                   .map(user -> modelMapper.map(user, UserDTO.class))
                   .toList();*/
           List<UserResponse> userResponse =users.stream().map(user->new UserResponse(user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getAddress())).toList();
           ApiResponse<List<UserResponse>> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "Fetching Successfully..",userResponse);
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
            List<User> users = userRepository.findAll();
            if(users.isEmpty()){
                throw new ResourceNotFoundException("Content not Exists.");
            }
          /* List<UserDTO> list = users.stream()
                   .map(user -> modelMapper.map(user, UserDTO.class))
                   .toList();*/
            List<UserResponse> userResponse =users.stream().map(user->new UserResponse(user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getAddress())).toList();
            ApiResponse<List<UserResponse>> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "Fetching Successfully..",userResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }catch (Exception e) {
            log.error("Failed to fetching all users: {}", e.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> deleteUser(int id) throws ServiceLogicException, ResourceNotFoundException{
        try {
            log.info("Deleting user with id: {}",id);
            User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
            userRepository.delete(user);
            ApiResponse<UserResponse> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(),ApiResponseStatus.SUCCESS.name(), "User Deleted Successfully..",null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete user account: {}", e.getMessage());
            throw new ServiceLogicException();
        }
    }
}