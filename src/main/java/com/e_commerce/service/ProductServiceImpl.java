package com.e_commerce.service;

import com.e_commerce.dto.ProductDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.mapper.ProductMapper;
import com.e_commerce.model.Product;
import com.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDto getProductDetailsByProductId(Integer productId) throws ResultQueryException {

        Product product = productRepository.getProductDetailsByProductId(productId).orElseThrow(() -> new ResultQueryException("Nessun prodotto trovato con id: " + productId));

        return productMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> findAllProduct() {

        List<Product> productList = productRepository.findAllProduct();

        return productMapper.toProductDtoList(productList);
    }
}
