package com.e_commerce.repository;

import com.e_commerce.model.Cart;

import java.util.List;

public interface CartRepository {

    void addToCart (Cart cart);

    List<Cart> findAllProductByUser (String fiscalCode);

    void removeToCart (Integer productId);

    void deleteAllByUser (String fiscalCode);
}
