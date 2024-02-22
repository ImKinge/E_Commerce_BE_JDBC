package com.e_commerce.repository;

import com.e_commerce.model.Favourites;
import com.e_commerce.repository.rowmapper.FavouritesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public class FavouritesRepositoryImpl implements FavouritesRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${database.schema}")
    private String databaseSchema;

    private final String table = "favourites";

    @Override
    public void saveFavourites(Favourites favourites) {
        String sql = "INSERT INTO " + databaseSchema + "." + table + " (product_id , user_fc) VALUES (?, ?)";

        jdbcTemplate.update(sql, favourites.getProductId(), favourites.getFiscalCode());
    }

    @Override
    public void deleteByProductId(Integer productId) {

        String sql = "DELETE FROM " + databaseSchema + "." + table + " WHERE product_id = ?";

        jdbcTemplate.update(sql, productId);

    }

    @Override
    public List<Favourites> findAllFavouritesByUser(String fiscalCode) {

        String sql = "SELECT * FROM " + databaseSchema + "." + table + " WHERE user_fc = ?";

        List<Favourites> favouritesList = jdbcTemplate.query(sql, new FavouritesRowMapper(), fiscalCode);

        if(favouritesList.isEmpty()) {
            return null;
        } else {
            return favouritesList;
        }

    }

    /**
     * In questo metodo abbiamo usato una query per tornare una lista perchè altrimenti
     * se il record che desideriamo non lo trova torna una EmptyResultDataAccessException
     * che non ci permette di gestire una query custom
     * @param productId
     * @param fiscalCode
     * @return
     */
    @Override
    public Favourites findByProductIdAndUser(Integer productId, String fiscalCode) {

        String sql = "SELECT * FROM " + databaseSchema + "." + table + " WHERE product_id = ? AND user_fc = ?";

        List<Favourites> favouritesList = jdbcTemplate.query(sql, new FavouritesRowMapper(), productId, fiscalCode);

        if (favouritesList.isEmpty()) {
            return null;
        } else  { // list contains exactly 1 element
            return favouritesList.get(0);
        }
    }
}
