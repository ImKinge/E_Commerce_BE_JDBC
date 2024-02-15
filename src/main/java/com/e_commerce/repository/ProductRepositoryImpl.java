package com.e_commerce.repository;

import com.e_commerce.model.Product;
import com.e_commerce.repository.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${database.schema}")
    private String databaseSchema;

    private String table = "product";

    @Override
    public Optional<Product> getProductDetailsByProductId(Integer productId) {

        String sql = "SELECT * FROM " + databaseSchema + "." + table + " WHERE id = ?";

        List<Product> productList = jdbcTemplate.query(sql, new ProductRowMapper(), productId);
        return productList.stream().findFirst();

    }

    @Override
    public List<Product> findAllProduct() {

        String sql = "SELECT * FROM " + databaseSchema + "." + table;
        List<Product> productList = jdbcTemplate.query(sql, new ProductRowMapper());
        return productList;
    }
}
