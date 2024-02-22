package com.e_commerce.controller;

import com.e_commerce.dto.CartDto;
import com.e_commerce.dto.ResponseDto;
import com.e_commerce.exception.CartException;
import com.e_commerce.exception.ResultQueryException;

import com.e_commerce.security.jwt.JWTGenerator;
import com.e_commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JWTGenerator jwtGenerator;


    @GetMapping("/add-to-cart")
    public ResponseEntity<?> addToCart (@RequestParam Integer productId,
                                        @RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        try {
            CartDto cartDto = cartService.addToCart(productId, fiscalCode);
            return new ResponseEntity<>(new ResponseDto<>(cartDto, true), HttpStatus.OK);
        } catch (ResultQueryException e) {
            return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-cart-details")
    public ResponseEntity<?> getCartDetails (@RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        try {
            List<CartDto> cartDtoList = cartService.findAllProductByUser(fiscalCode);
            return new ResponseEntity<>(new ResponseDto<>(cartDtoList, true), HttpStatus.OK);
        } catch (ResultQueryException e) {
            return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        } catch (CartException e) {
            return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), false), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-to-cart")
    public ResponseEntity<?> removeItemFromCart(@RequestParam Integer cartItemId) {

        cartService.deleteByProductId(cartItemId);

        return new ResponseEntity<>(new ResponseDto<>("Rimozione avvenuta con successo", true), HttpStatus.OK);
    }
}
