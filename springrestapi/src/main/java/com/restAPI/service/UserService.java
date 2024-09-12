package com.restAPI.service;

import com.restAPI.dto.UserRequest;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.responseModel.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public interface UserService {

   ResponseEntity<ApiResponse<?>> createUser(UserRequest request)throws BadRequestException, ServiceLogicException;

    ResponseEntity<ApiResponse<?>> updateUser(int id,UserRequest request) throws BadRequestException,ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getUserById(int id) throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getUserByName(String name) throws BadRequestException,ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getAllUser() throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getUsers() throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> deleteUser(int id) throws ServiceLogicException, ResourceNotFoundException;

}
