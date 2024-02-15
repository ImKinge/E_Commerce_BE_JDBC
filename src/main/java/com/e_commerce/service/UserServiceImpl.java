package com.e_commerce.service;

import com.e_commerce.dto.UserDataDto;
import com.e_commerce.exception.ResultQueryException;
import com.e_commerce.mapper.UserMapper;

import com.e_commerce.model.UserData;
import com.e_commerce.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDataDto getUserDetailsByFiscalCode(String fiscalCode) throws ResultQueryException {

        UserData userData = userDataRepository.getUserDetailsByFiscalCode(fiscalCode);

        if(userData == null) {
            throw new ResultQueryException("Nessun utente trovato con codice fiscale: " + fiscalCode);
        }

        return userMapper.toUserDto(userData);
    }

    @Override
    public UserDataDto findUserByUsername(String username) throws ResultQueryException {

        UserData userData = userDataRepository.findByUsername(username).orElseThrow(() -> new ResultQueryException("Nessun utente trovato con username: " + username));

        return userMapper.toUserDto(userData);
    }

}
