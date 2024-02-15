package com.e_commerce.repository.rowmapper;

import com.e_commerce.model.Favourites;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavouritesRowMapper implements RowMapper<Favourites> {


    @Override
    public Favourites mapRow(ResultSet rs, int rowNum) throws SQLException {

        Favourites favourites = new Favourites();

        favourites.setProductId(rs.getInt("product_id"));
        favourites.setFiscalCode(rs.getString("user_fc"));

        return favourites;
    }
}
