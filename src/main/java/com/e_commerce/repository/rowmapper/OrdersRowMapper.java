package com.e_commerce.repository.rowmapper;

import com.e_commerce.model.Orders;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrdersRowMapper implements RowMapper<Orders> {

    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {

        Orders orders = new Orders();

        orders.setId(rs.getInt("id"));
        orders.setProductId(rs.getInt("product_id"));
        orders.setFiscalCode(rs.getString("user_id"));
        orders.setPurchaseDate(rs.getObject("purchase_date", LocalDateTime.class));

        return orders;
    }
}
