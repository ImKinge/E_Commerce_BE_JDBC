package com.e_commerce.service;

import com.e_commerce.dto.CartDto;
import com.e_commerce.exception.CartException;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.mapper.CartMapper;
import com.e_commerce.model.Cart;
import com.e_commerce.model.Product;
import com.e_commerce.model.UserData;
import com.e_commerce.repository.CartRepository;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private CartMapper cartMapper;

    @Transactional
    @Override
    public CartDto addToCart(Integer productId, String fiscalCode) throws ResultQueryException {

        Product product = productRepository.getProductDetailsByProductId(productId).orElseThrow(() -> new ResultQueryException("Prodotto inesistente, impossibile aggiungerlo al carrello!"));
        UserData userData = userDataRepository.getUserDetailsByFiscalCode(fiscalCode).orElseThrow(() -> new ResultQueryException("Nessun utente trovato con codice fiscale: " + fiscalCode));

        Cart cart = new Cart(userData.getFiscalCode(), product.getId());
        cartRepository.addToCart(cart);

        return cartMapper.toCartDto(cart);
    }

    @Transactional
    @Override
    public void deleteByProductId(Integer productId) {

        cartRepository.removeToCart(productId);
    }

    @Override
    public List<CartDto> findAllProductByUser(String fiscalCode) throws CartException, ResultQueryException {

        List<Cart> cartList = cartRepository.findAllProductByUser(fiscalCode);

        if(cartList.isEmpty()) {
            throw new CartException("Non ci sono prodotti nel carrello!");
        }

        return cartMapper.toCartDtoList(cartList);
    }
}
