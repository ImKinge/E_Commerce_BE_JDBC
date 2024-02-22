package com.e_commerce.controller;

import com.e_commerce.dto.OrdersDto;
import com.e_commerce.dto.ResponseDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Orders;
import com.e_commerce.security.jwt.JWTGenerator;
import com.e_commerce.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private JWTGenerator jwtGenerator;


    @PostMapping("/add-to-orders")
    private ResponseEntity<?> addProductToOrders (@RequestBody List<Orders> ordersList,
                                                  @RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        try {
            List<OrdersDto> ordersDtoList = ordersService.addToOrders(fiscalCode, ordersList);
            return new ResponseEntity<>(new ResponseDto<>(ordersDtoList, true), HttpStatus.OK);

        } catch (ResultQueryException e) {
            return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<?> getAllOrders (@RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        try {
            List<OrdersDto> ordersDtoList = ordersService.getOrdersByUser(fiscalCode);
            return new ResponseEntity<>(new ResponseDto<>(ordersDtoList, true), HttpStatus.OK);

        } catch (ResultQueryException e) {
            return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}
