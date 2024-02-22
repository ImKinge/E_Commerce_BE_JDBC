package com.e_commerce.service;

import com.e_commerce.dto.CartDto;
import com.e_commerce.exception.CartException;
import com.e_commerce.exception.ResultQueryException;

import java.util.List;

public interface CartService {

    CartDto addToCart (Integer productId, String fiscalCode) throws ResultQueryException;

    void deleteByProductId (Integer productId);

    List<CartDto> findAllProductByUser (String fiscalCode) throws CartException, ResultQueryException;
}
