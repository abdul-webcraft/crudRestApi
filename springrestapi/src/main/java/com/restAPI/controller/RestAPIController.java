package com.restAPI.controller;

import com.restAPI.dto.UserRequest;
import com.restAPI.dto.UserResponse;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.responseModel.ApiResponse;
import com.restAPI.responseModel.ApiResponseStatus;
import com.restAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/")
public class RestAPIController {

    @Autowired
    private UserService userService;

    @GetMapping("admin/users")
    public ResponseEntity<ApiResponse<?>> GetUsers()throws ServiceLogicException, ResourceNotFoundException{
        return userService.getUsers();
    }

    @GetMapping("users")
    public ResponseEntity<ApiResponse<?>> GetAllUser()throws ServiceLogicException, ResourceNotFoundException{
        return userService.getAllUser();
    }

    @GetMapping("admin/user/{id}")
    public ResponseEntity<ApiResponse<?>> user(@PathVariable int id)throws ServiceLogicException, ResourceNotFoundException{
        return userService.getUserById(id);
    }

    @GetMapping("user/{name}")
    public ResponseEntity<ApiResponse<?>> userByName(@PathVariable String name)throws ServiceLogicException, ResourceNotFoundException{
       return userService.getUserByName(name);
    }

    @PostMapping("admin/user")
    public ResponseEntity<ApiResponse<?>> createUser(@Valid @RequestBody UserRequest request) throws BadRequestException, ServiceLogicException{
      return userService.createUser(request);
    }

    @PutMapping("admin/user/{id}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable int id,@Valid @RequestBody UserRequest request) throws BadRequestException,ServiceLogicException, ResourceNotFoundException{
          return userService.updateUser(id,request);
    }

    @DeleteMapping("admin/user/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable int id) throws ServiceLogicException,ResourceNotFoundException {
        return userService.deleteUser(id);
    }

}
