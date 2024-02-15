package com.e_commerce.repository;

import com.e_commerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> getProductDetailsByProductId (Integer productId);

    List<Product> findAllProduct ();
}
