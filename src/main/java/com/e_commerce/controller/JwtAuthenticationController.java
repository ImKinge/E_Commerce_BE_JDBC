package com.e_commerce.controller;

import com.e_commerce.dto.*;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.repository.UserDataRepository;
import com.e_commerce.security.jwt.JWTGenerator;
import com.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class JwtAuthenticationController  {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {

        try{
            UserDataDto userDto = userService.findUserByUsername(jwtRequest.getUsername());

            /*
            Qui andiamo a confrontare se la password encodata salvata sul db
            corrisponde con quella decodata che inseriamo nella request
             */
            boolean passwordCheck = BCrypt.checkpw(jwtRequest.getPassword(), userDto.getPassword());
            if(!passwordCheck) {
                throw new ResultQueryException("Password Errata");
            }

            String fiscalCode = userDto.getFiscalCode();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication.getName(), fiscalCode);
            AuthResponseDto authResponseDto = new AuthResponseDto(token);

            return new ResponseEntity<>(new ResponseDto<>(authResponseDto, true), HttpStatus.OK);
        } catch (ResultQueryException ex) {
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }


}
