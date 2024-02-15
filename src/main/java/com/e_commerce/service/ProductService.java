package com.e_commerce.service;

import com.e_commerce.dto.ProductDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto getProductDetailsByProductId (Integer productId) throws ResultQueryException;

    List<ProductDto> findAllProduct();
}
