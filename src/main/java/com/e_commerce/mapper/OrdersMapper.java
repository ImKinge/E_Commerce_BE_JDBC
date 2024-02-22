package com.e_commerce.mapper;

import com.e_commerce.dto.OrdersDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Orders;

import java.util.List;

public interface OrdersMapper {

    OrdersDto toOrdersDto (Orders orders) throws ResultQueryException;

    List<OrdersDto> toOrdersDtoList (List<Orders> ordersList) throws ResultQueryException;
}
