package com.restAPI.controller;

import com.restAPI.dto.AddressRequest;
import com.restAPI.dto.UserRequestDTO;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.exception.UniqueConstraintException;
import com.restAPI.responseModel.ApiResponse;
import com.restAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class RestAPIController {

    @Autowired
    private UserService userService;

    @GetMapping("admin/users")
    public ResponseEntity<ApiResponse<?>> getUsers() throws ServiceLogicException, ResourceNotFoundException{
        log.info("Fetching all users");
        return userService.getUsers();
    }

    @GetMapping("users")
    public ResponseEntity<ApiResponse<?>> getAllUser()throws ServiceLogicException, ResourceNotFoundException{
        log.info("Fetching all users for non-admin");
        return userService.getAllUser();
    }

    @GetMapping("user/{id}")
    public ResponseEntity<ApiResponse<?>> userById(@PathVariable int id)throws ServiceLogicException, ResourceNotFoundException{
        log.info("Fetching user with ID: {}", id);
        return userService.getUserById(id);
    }

//    @GetMapping("user/{name}")
//    public ResponseEntity<ApiResponse<?>> userByName(@PathVariable String name)throws ServiceLogicException, ResourceNotFoundException{
//        log.info("Fetching user with name: {}", name);
//       return userService.getUserByName(name);
//    }

    @PostMapping("user")
    public ResponseEntity<ApiResponse<?>> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws BadRequestException, ServiceLogicException, UniqueConstraintException {
        log.info("Creating user with details: {}", userRequestDTO);
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable int id,@Valid @RequestBody UserRequestDTO userRequestDTO) throws BadRequestException,ServiceLogicException, ResourceNotFoundException,UniqueConstraintException{
        log.info("Updating user with ID: {} and details: {}", id, userRequestDTO);
          return userService.updateUser(id,userRequestDTO);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable int id) throws ServiceLogicException,ResourceNotFoundException {
        log.info("Deleting user with ID: {}", id);
        return userService.deleteUser(id);
    }

    @PostMapping("address")
    public ResponseEntity<ApiResponse<?>> createAddress(@Valid @RequestBody AddressRequest addressRequest) throws BadRequestException, ServiceLogicException {
        log.info("Creating address with details: {}", addressRequest);
        return userService.createAddress(addressRequest);
    }

}
