package com.e_commerce.repository.rowmapper;

import com.e_commerce.model.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {

        Cart cart = new Cart();

        cart.setId(rs.getInt("id"));
        cart.setProductId(rs.getInt("product_id"));
        cart.setFiscalCode(rs.getString("user_data_fiscal_code"));

        return cart;
    }
}
