package com.e_commerce.repository;

import com.e_commerce.model.Orders;

import java.util.List;

public interface OrdersRepository {

    void addToOrders (List<Orders> orders);

    List<Orders> getOrdersByUser(String fiscalCode);
}
