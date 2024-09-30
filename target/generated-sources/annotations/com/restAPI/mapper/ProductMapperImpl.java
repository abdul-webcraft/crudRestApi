package com.restAPI.mapper;

import com.restAPI.dto.ProductRequestDTO;
import com.restAPI.dto.ProductResponseDTO;
import com.restAPI.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-30T11:08:30+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productRequestDTOToProduct(ProductRequestDTO productRequestDTO) {
        if ( productRequestDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productRequestDTO.getId() );
        product.setName( productRequestDTO.getName() );
        product.setBrand( productRequestDTO.getBrand() );
        product.setPrice( productRequestDTO.getPrice() );
        product.setQuantity( productRequestDTO.getQuantity() );

        return product;
    }

    @Override
    public ProductResponseDTO productToProductResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId( product.getId() );
        productResponseDTO.setName( product.getName() );
        productResponseDTO.setBrand( product.getBrand() );
        productResponseDTO.setPrice( product.getPrice() );
        productResponseDTO.setQuantity( product.getQuantity() );

        return productResponseDTO;
    }

    @Override
    public ProductRequestDTO productToProductRequestDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductRequestDTO productRequestDTO = new ProductRequestDTO();

        productRequestDTO.setId( product.getId() );
        productRequestDTO.setName( product.getName() );
        productRequestDTO.setBrand( product.getBrand() );
        productRequestDTO.setPrice( product.getPrice() );
        productRequestDTO.setQuantity( product.getQuantity() );

        return productRequestDTO;
    }

    @Override
    public List<ProductResponseDTO> prodectListToProductResponseDTOList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponseDTO> list = new ArrayList<ProductResponseDTO>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductResponseDTO( product ) );
        }

        return list;
    }

    @Override
    public List<ProductRequestDTO> prodectListToProductRequestDTOList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductRequestDTO> list = new ArrayList<ProductRequestDTO>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductRequestDTO( product ) );
        }

        return list;
    }
}
