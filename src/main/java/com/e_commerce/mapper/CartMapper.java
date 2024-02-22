package com.e_commerce.mapper;

import com.e_commerce.dto.CartDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Cart;

import java.util.List;

public interface CartMapper {

    CartDto toCartDto (Cart cart) throws ResultQueryException;

    List<CartDto> toCartDtoList (List<Cart> cartList) throws ResultQueryException;
}
