package com.e_commerce.mapper;

import com.e_commerce.dto.CartDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Cart;
import com.e_commerce.model.Product;
import com.e_commerce.model.UserData;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapperImpl implements CartMapper{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserDataRepository userDataRepository;


    @Override
    public CartDto toCartDto(Cart cart) throws ResultQueryException {

        if(cart == null) {
            return null;
        }

        CartDto cartDto = new CartDto();

        UserData fiscalCode = userDataRepository.getUserDetailsByFiscalCode(cart.getFiscalCode()).orElseThrow(() -> new ResultQueryException("Nessun utente trovato con codice fiscale: " + cart.getFiscalCode()));
        Product product = productRepository.getProductDetailsByProductId(cart.getProductId()).orElseThrow(() -> new ResultQueryException("Nessun prodotto trovato con id: " + cart.getProductId()));

        cartDto.setId(cart.getId());
        cartDto.setUserData(fiscalCode);
        cartDto.setProduct(product);

        return cartDto;
    }

    @Override
    public List<CartDto> toCartDtoList(List<Cart> cartList) throws ResultQueryException {

        List<CartDto> cartDtoList = new ArrayList<>();

        for(Cart cart : cartList) {
            cartDtoList.add(toCartDto(cart));
        }

        return cartDtoList;
    }
}
