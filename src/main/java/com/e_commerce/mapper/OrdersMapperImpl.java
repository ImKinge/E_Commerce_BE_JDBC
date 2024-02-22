package com.e_commerce.mapper;


import com.e_commerce.dto.OrdersDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Orders;
import com.e_commerce.model.Product;
import com.e_commerce.model.UserData;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersMapperImpl implements OrdersMapper {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Dal momento che utilizziamo JDBC template negli oggetti costruiti avrò dati secchi come ad esempio UserData,
     * non avrò tutto l'oggetto ma bensì la sua chiave primaria
     * e quindi dato che faccio tornare un dto devo fare una chiamata al repository
     * per ottenere l'oggetto e mapparlo nel dto
     *
     * @param orders
     * @return OrdersDto
     * @throws ResultQueryException eccezione
     */
    @Override
    public OrdersDto toOrdersDto(Orders orders) throws ResultQueryException {

        OrdersDto ordersDto = new OrdersDto();

        UserData userData = userDataRepository.getUserDetailsByFiscalCode(orders.getFiscalCode()).orElseThrow(() -> new ResultQueryException("Nessun utente trovato con codice fiscale: "+ orders.getFiscalCode()));
        Product product = productRepository.getProductDetailsByProductId(orders.getProductId()).orElseThrow(() -> new ResultQueryException("Nessun Prodotto trovato con id prodotto: " + orders.getProductId()));

        ordersDto.setUser(userData);
        ordersDto.setProduct(product);
        ordersDto.setPurchaseDate(orders.getPurchaseDate());

        return ordersDto;
    }

    @Override
    public List<OrdersDto> toOrdersDtoList(List<Orders> ordersList) throws ResultQueryException {

        List<OrdersDto> ordersDtoList = new ArrayList<>();

        for(Orders orders : ordersList) {
            ordersDtoList.add(toOrdersDto(orders));
        }

        return ordersDtoList;
    }


}
