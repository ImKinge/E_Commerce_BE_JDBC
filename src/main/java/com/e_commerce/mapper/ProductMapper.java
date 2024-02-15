package com.e_commerce.mapper;

import com.e_commerce.dto.ProductDto;
import com.e_commerce.model.Product;

import java.util.List;

public interface ProductMapper {

    ProductDto toProductDto (Product product);

    List<ProductDto> toProductDtoList (List<Product> productList);
}
