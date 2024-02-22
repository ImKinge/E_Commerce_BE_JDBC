package com.e_commerce.controller;

import com.e_commerce.dto.ResponseDto;
import com.e_commerce.dto.UserDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.security.jwt.JWTGenerator;
import com.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class UserDataController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTGenerator jwtGenerator;


    @GetMapping("/find-by-fiscal-code")
    public ResponseEntity<?> getUserDetailsByFiscalCode (@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        try {
            UserDto userDto = userService.getUserDetailsByFiscalCode(fiscalCode);
            return  new ResponseEntity<>(new ResponseDto<>(userDto, true), HttpStatus.OK);
        } catch (ResultQueryException ex) {
            return  new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

    }
}
