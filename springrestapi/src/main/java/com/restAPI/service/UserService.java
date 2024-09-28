package com.restAPI.service;

import com.restAPI.dto.AddressRequest;
import com.restAPI.dto.UserRequestDTO;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.exception.UniqueConstraintException;
import com.restAPI.responseModel.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public interface UserService {

   ResponseEntity<ApiResponse<?>> createUser(UserRequestDTO userRequestDTO) throws BadRequestException, ServiceLogicException, UniqueConstraintException;

    ResponseEntity<ApiResponse<?>> updateUser(int id,UserRequestDTO userRequestDTO) throws BadRequestException,ServiceLogicException, ResourceNotFoundException,UniqueConstraintException;

    ResponseEntity<ApiResponse<?>> getUserById(int id) throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getUserByName(String name) throws BadRequestException,ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getAllUser() throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getUsers() throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> deleteUser(int id) throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> createAddress(AddressRequest addressRequest) throws BadRequestException, ServiceLogicException;

}
