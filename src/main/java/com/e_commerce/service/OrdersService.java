package com.e_commerce.service;

import com.e_commerce.dto.OrdersDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Orders;

import java.util.List;

public interface OrdersService {


    List<OrdersDto> addToOrders(String fiscalCode, List<Orders> ordersList) throws ResultQueryException;

    List<OrdersDto> getOrdersByUser(String fiscalCode) throws ResultQueryException;
}
