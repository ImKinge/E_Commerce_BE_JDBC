package com.e_commerce.service;

import com.e_commerce.dto.FavouritesDto;
import com.e_commerce.exception.FavouritesException;
import com.e_commerce.exception.ResultQueryException;

import java.util.List;

public interface FavouritesService {

    FavouritesDto addFavourite (Integer productId, String fiscalCode) throws ResultQueryException, FavouritesException;

    void deleteByProductId (Integer productId);

    List<FavouritesDto> findAllFavouritesByUser (String fiscalCode) throws FavouritesException, ResultQueryException;
}
