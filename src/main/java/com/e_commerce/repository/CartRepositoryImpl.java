package com.e_commerce.repository;

import com.e_commerce.model.Cart;
import com.e_commerce.repository.rowmapper.CartRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${database.schema}")
    private String databaseSchema;

    private final String table = "cart";


    @Override
    public void addToCart(Cart cart) {

        String sql = "INSERT INTO " + databaseSchema + "." + table + " (product_id, user_data_fiscal_code) VALUES (?, ?)";

        jdbcTemplate.update(sql, cart.getProductId(), cart.getFiscalCode());
    }

    @Override
    public List<Cart> findAllProductByUser(String fiscalCode) {

        String sql = "SELECT * FROM " + databaseSchema + "." + table + " WHERE user_data_fiscal_code = ?";

        return jdbcTemplate.query(sql, new CartRowMapper(), fiscalCode);
    }

    @Override
    public void removeToCart(Integer productId) {

        String sql = "DELETE FROM " + databaseSchema + "." + table + " WHERE product_id = ?";

        jdbcTemplate.update(sql, productId);

    }

    @Override
    public void deleteAllByUser(String fiscalCode) {

        String sql = "DELETE FROM " + databaseSchema + "." + table + " WHERE user_data_fiscal_code = ?";

        jdbcTemplate.update(sql, fiscalCode);
    }
}
