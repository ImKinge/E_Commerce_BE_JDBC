package com.e_commerce.service;

import com.e_commerce.dto.OrdersDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.mapper.OrdersMapper;
import com.e_commerce.model.Orders;
import com.e_commerce.model.Product;
import com.e_commerce.model.UserData;
import com.e_commerce.repository.CartRepository;
import com.e_commerce.repository.OrdersRepository;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrdersMapper ordersMapper;


    @Transactional
    @Override
    public List<OrdersDto> addToOrders(String fiscalCode, List<Orders> ordersList) throws ResultQueryException {

        if(!ordersList.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.now();

            for (Orders orders : ordersList) {
                orders.setPurchaseDate(localDateTime);
            }

            ordersRepository.addToOrders(ordersList);
            cartRepository.deleteAllByUser(fiscalCode);
        } else {
            throw new ResultQueryException("Non puoi effettuare un ordine se ci sono 0 articoli nel carrello!");
        }

        return ordersMapper.toOrdersDtoList(ordersList);
    }

    @Override
    public List<OrdersDto> getOrdersByUser(String fiscalCode) throws ResultQueryException {

        List<Orders> ordersList = ordersRepository.getOrdersByUser(fiscalCode);

        if(ordersList.isEmpty()) {
            throw new ResultQueryException("Nessun ordine presente al momento");
        }

        return ordersMapper.toOrdersDtoList(ordersList);
    }
}
