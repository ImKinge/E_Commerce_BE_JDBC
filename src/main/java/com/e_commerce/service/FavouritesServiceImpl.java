package com.e_commerce.service;

import com.e_commerce.exception.FavouritesException;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.dto.FavouritesDto;
import com.e_commerce.mapper.FavouritesMapper;
import com.e_commerce.model.Favourites;
import com.e_commerce.model.Product;
import com.e_commerce.model.UserData;
import com.e_commerce.repository.FavouritesRepository;
import com.e_commerce.repository.ProductRepository;
import com.e_commerce.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService{

    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FavouritesMapper favouritesMapper;

    @Transactional
    @Override
    public FavouritesDto addFavourite(Integer productId, String fiscalCode) throws ResultQueryException, FavouritesException {

        Product product = productRepository.getProductDetailsByProductId(productId).orElseThrow(() -> new ResultQueryException("Nessun prodotto trovato con id: " + productId));


        UserData userData = userDataRepository.getUserDetailsByFiscalCode(fiscalCode);
        if(userData == null) {
           throw  new ResultQueryException("Nessun utente trovato con codice fiscale: " + fiscalCode);
        }

        Favourites favouritesThrow = favouritesRepository.findByProductIdAndUser(productId, fiscalCode);
        if(favouritesThrow != null) {
            throw new FavouritesException("Prodotto già presente tra i preferiti!");
        }

        Favourites favourites = new Favourites(userData.getFiscalCode(), product.getId());
        favouritesRepository.saveFavourites(favourites);

        return favouritesMapper.toFavouritesDto(favourites);
    }

    @Transactional
    @Override
    public void deleteByProductId(Integer productId) {
        favouritesRepository.deleteByProductId(productId);
    }

    @Override
    public List<FavouritesDto> findAllFavouritesByUser(String fiscalCode) throws FavouritesException, ResultQueryException {

        List<Favourites> favouritesList = favouritesRepository.findAllFavouritesByUser(fiscalCode);

        if(favouritesList == null) {
            throw new FavouritesException("Non ci sono preferiti salvati!");
        }
        return favouritesMapper.toFavouritesDtoList(favouritesList);
    }
}
