package com.e_commerce.repository;

import com.e_commerce.model.Orders;
import com.e_commerce.repository.rowmapper.OrdersRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrdersRepositoryImpl implements OrdersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${database.schema}")
    private String databaseSchema;

    private final String table = "orders";


    @Override
    public void addToOrders(List<Orders> ordersList) {

        for(Orders order : ordersList) {
            String sql = "INSERT INTO " + databaseSchema + "." + table + " (product_id, user_id, purchase_date) VALUES (?, ?, ?)";

            jdbcTemplate.update(sql, order.getProductId(), order.getFiscalCode(), order.getPurchaseDate());
        }

    }

    @Override
    public List<Orders> getOrdersByUser(String fiscalCode) {

        String sql= "SELECT * FROM " + databaseSchema + "." + table + " WHERE user_id = ?";

        return jdbcTemplate.query(sql, new OrdersRowMapper(), fiscalCode);
    }
}
