package com.e_commerce.repository;

import com.e_commerce.model.Favourites;

import java.util.List;
import java.util.Set;

public interface FavouritesRepository {

    void saveFavourites (Favourites favourites);

    void deleteByProductId (Integer productId);

    List<Favourites> findAllFavouritesByUser (String fiscalCode);

    Favourites findByProductIdAndUser(Integer productId, String fiscalCode);
}
