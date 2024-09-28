package com.restAPI.service;

import com.restAPI.dto.ProductRequestDTO;
import com.restAPI.dto.ProductResponseDTO;
import com.restAPI.exception.BadRequestException;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.exception.ServiceLogicException;
import com.restAPI.exception.UniqueConstraintException;
import com.restAPI.mapper.ProductMapper;
import com.restAPI.model.Product;
import com.restAPI.repository.ProductRepository;
import com.restAPI.responseModel.ApiResponse;
import com.restAPI.responseModel.ApiResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper=ProductMapper.INSTANCE;

    @Override
    public ResponseEntity<ApiResponse<?>> createProduct(ProductRequestDTO productRequestDTO) throws BadRequestException, ServiceLogicException, UniqueConstraintException {
        try {
            log.info("Creating product with name: {}", productRequestDTO.getName());
            Product product = productMapper.productRequestDTOToProduct(productRequestDTO);
            System.out.println("product:"+product);
            // Save user and map to response DTO
            Product saveProduct = productRepository.save(product);
            System.out.println("product:"+saveProduct);
            ProductResponseDTO  productResponseDTO = productMapper.productToProductResponseDTO(saveProduct);
//            ProductRequestDTO productResponseDTO1=productMapper.productToProductRequestDTO(saveProduct);
            ApiResponse<ProductResponseDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), ApiResponseStatus.SUCCESS.name(), "Product Created Successfully..", productResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception ex) {
            log.error("Failed to create new product account: {}", ex.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateProduct(int id, ProductRequestDTO productRequestDTO) throws BadRequestException, ServiceLogicException, ResourceNotFoundException, UniqueConstraintException {
        try {
            log.info("Updating product with id: {}",id);
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not existed with given id :" + id));
            productRequestDTO.setId(product.getId());
            Product updateProduct = productMapper.productRequestDTOToProduct(productRequestDTO);
            Product saveProduct = productRepository.save(updateProduct);
            ProductResponseDTO productResponseDTO = productMapper.productToProductResponseDTO(saveProduct);
            var response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "Product Updated Successfully..", productResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }catch(Exception e) {
            log.error("Failed to update product: {}", e.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getProductById(int id) throws ServiceLogicException, ResourceNotFoundException {
        try {
            log.info("Get product with id: {}",id);
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(("Product not existed with given id :" + id)));
//            ProductRequestDTO productRequestDTO = productMapper.productToProductRequestDTO(product);
            ProductResponseDTO productResponseDTO=productMapper.productToProductResponseDTO(product);
            ApiResponse<ProductResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "Product Fetch Successfully..", productResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }catch(Exception e) {
            log.error("Failed to fetch product: {}",e.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllProduct() throws ServiceLogicException, ResourceNotFoundException {
        try {
            log.info("Get all product");
            List<Product> products = productRepository.findAll();
            if(products.isEmpty()){
                throw new ResourceNotFoundException("Content not Exists");
            }
            List<ProductRequestDTO> productRequestDTOS = productMapper.prodectListToProductRequestDTOList(products);
//            List<ProductResponseDTO> productResponseDTOS = products.stream()
//                    .map(product ->productMapper.productToProductResponseDTO(product)).collect(Collectors);
//            List<ProductResponseDTO> productResponseDTOS = productMapper.prodectListToProductResponseDTOList(products);
            ApiResponse<List<ProductRequestDTO>> response = new ApiResponse<>(HttpStatus.OK.value(),ApiResponseStatus.SUCCESS.name(), "Fetching Successfully..",productRequestDTOS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }catch (Exception e) {
            log.error("Failed to fetch all product: {}", e.getMessage());
            throw new ServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> deleteProduct(int id) throws ServiceLogicException, ResourceNotFoundException {
        try {
            log.info("Deleting product with id: {}",id);
            var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
            productRepository.delete(product);
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(),ApiResponseStatus.SUCCESS.name(), "Product Deleted Successfully..","Product Deleted...");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to deletion of product: {}", e.getMessage());
            throw new ServiceLogicException();
        }
    }
}