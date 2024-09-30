package com.restAPI.mapper;

import com.restAPI.dto.ProductRequestDTO;
import com.restAPI.dto.ProductResponseDTO;
import com.restAPI.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productRequestDTOToProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO productToProductResponseDTO(Product product);


    ProductRequestDTO productToProductRequestDTO(Product product);

    List<ProductResponseDTO> prodectListToProductResponseDTOList(List<Product> products);

    List<ProductRequestDTO> prodectListToProductRequestDTOList(List<Product> products);

}
