package com.e_commerce.mapper;



import com.e_commerce.dto.FavouritesDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Favourites;

import java.util.List;
import java.util.Set;

public interface FavouritesMapper {

    FavouritesDto toFavouritesDto (Favourites favourites) throws ResultQueryException;

    List<FavouritesDto> toFavouritesDtoList (List<Favourites> favouritesList) throws ResultQueryException;
}
