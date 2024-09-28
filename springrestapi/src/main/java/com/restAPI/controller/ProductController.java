package com.restAPI.controller;

import com.restAPI.dto.ProductRequestDTO;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.exception.UniqueConstraintException;
import com.restAPI.responseModel.ApiResponse;
import com.restAPI.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public ResponseEntity<ApiResponse<?>> getAllProduct()throws ServiceLogicException, ResourceNotFoundException{
        log.info("Fetching all product for non-admin");
        return productService.getAllProduct();
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ApiResponse<?>> productById(@PathVariable int id)throws ServiceLogicException, ResourceNotFoundException{
        log.info("Fetching product with ID: {}", id);
        return productService.getProductById(id);
    }

    @PostMapping("product")
    public ResponseEntity<ApiResponse<?>> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) throws BadRequestException, ServiceLogicException, UniqueConstraintException {
        log.info("Creating product with details: {}", productRequestDTO);
        return productService.createProduct(productRequestDTO);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<ApiResponse<?>> updateProduct(@PathVariable int id,@Valid @RequestBody ProductRequestDTO productRequestDTO) throws BadRequestException,ServiceLogicException, ResourceNotFoundException,UniqueConstraintException{
        log.info("Updating product with ID: {} and details: {}", id, productRequestDTO);
        return productService.updateProduct(id,productRequestDTO);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable int id) throws ServiceLogicException,ResourceNotFoundException {
        log.info("Deleting product with ID: {}", id);
        return productService.deleteProduct(id);
    }
}
