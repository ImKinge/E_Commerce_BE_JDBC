package com.e_commerce.controller;

import com.e_commerce.dto.FavouritesDto;
import com.e_commerce.dto.ResponseDto;
import com.e_commerce.exception.FavouritesException;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.security.jwt.JWTGenerator;
import com.e_commerce.service.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/favourites")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class FavouritesController {

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private FavouritesService favouritesService;

    @GetMapping("/add-to-favourites")
    public ResponseEntity<?> addToFavourites (@RequestParam Integer productId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        try {
            FavouritesDto favouritesDto = favouritesService.addFavourite(productId,fiscalCode);
            return new ResponseEntity<>(new ResponseDto<>(favouritesDto, true), HttpStatus.OK);
        } catch (FavouritesException ex) {
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        } catch (ResultQueryException ex) {
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/find-all-favourites-by-user")
    public ResponseEntity<?> findAllFavouritesByUser (@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        try {
            List<FavouritesDto> favouritesDtoList = favouritesService.findAllFavouritesByUser(fiscalCode);
            return new ResponseEntity<>(new ResponseDto<>(favouritesDtoList, true), HttpStatus.OK);
        } catch (FavouritesException ex) {
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        } catch (ResultQueryException ex) {
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-by-product-id")
    public ResponseEntity<?> deleteByProductId (@RequestParam Integer productId) {

        favouritesService.deleteByProductId(productId);
        return new ResponseEntity<>(new ResponseDto<>("Rimozione avvenuta con successo", true), HttpStatus.OK);
    }

}
