package com.e_commerce.mapper;

import com.e_commerce.dto.FavouritesDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.model.Favourites;
import com.e_commerce.model.Product;
import com.e_commerce.model.UserData;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FavouritesMapperImpl implements FavouritesMapper {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserDataRepository userDataRepository;


    @Override
    public FavouritesDto toFavouritesDto(Favourites favourites) throws ResultQueryException {

        if(favourites == null) {
            return null;
        }

        FavouritesDto favouritesDto = new FavouritesDto();

        UserData fiscalCode = userDataRepository.getUserDetailsByFiscalCode(favourites.getFiscalCode()).orElseThrow(() -> new ResultQueryException("Nessun utente trovato con codice fiscale: " + favourites.getFiscalCode()));
        Product product = productRepository.getProductDetailsByProductId(favourites.getProductId()).orElseThrow(() -> new ResultQueryException("Nessun prodotto trovato con id: " + favourites.getProductId()));

        favouritesDto.setId(favourites.getId());
        favouritesDto.setUser(fiscalCode);
        favouritesDto.setProduct(product);

        return favouritesDto;
    }

    @Override
    public List<FavouritesDto> toFavouritesDtoList(List<Favourites> favouritesList) throws ResultQueryException {

        List<FavouritesDto> favouritesDtoList = new ArrayList<>();

        for(Favourites favourites : favouritesList) {
            favouritesDtoList.add(toFavouritesDto(favourites));
        }

        return favouritesDtoList;
    }
}
