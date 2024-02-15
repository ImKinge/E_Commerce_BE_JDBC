package com.e_commerce.controller;

import com.e_commerce.dto.ResponseDto;
import com.e_commerce.dto.UserDataDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserDataController {

    @Autowired
    private UserService userService;


    @GetMapping("/find-by-fiscal-code")
    public ResponseEntity<?> getUserDetailsByFiscalCode (@RequestParam String fiscalCode) {

        try {
            UserDataDto userDataDto = userService.getUserDetailsByFiscalCode(fiscalCode);
            return  new ResponseEntity<>(new ResponseDto<>(userDataDto, true), HttpStatus.OK);
        } catch (ResultQueryException ex) {
            return  new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }

    }
}
