package com.restAPI.service;

import com.restAPI.dto.ProductRequestDTO;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.exception.UniqueConstraintException;
import com.restAPI.responseModel.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

   ResponseEntity<ApiResponse<?>> createProduct(ProductRequestDTO productRequestDTO) throws BadRequestException, ServiceLogicException, UniqueConstraintException;

    ResponseEntity<ApiResponse<?>> updateProduct(int id,ProductRequestDTO productRequestDTO) throws BadRequestException,ServiceLogicException, ResourceNotFoundException,UniqueConstraintException;

    ResponseEntity<ApiResponse<?>> getProductById(int id) throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> getAllProduct() throws ServiceLogicException, ResourceNotFoundException;

    ResponseEntity<ApiResponse<?>> deleteProduct(int id) throws ServiceLogicException, ResourceNotFoundException;

}
