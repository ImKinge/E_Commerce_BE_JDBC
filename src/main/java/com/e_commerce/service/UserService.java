package com.e_commerce.service;

import com.e_commerce.dto.UserDto;
import com.e_commerce.exception.ResultQueryException;

public interface UserService {

    UserDto getUserDetailsByFiscalCode (String fiscalCode) throws ResultQueryException;

    UserDto findUserByUsername (String username) throws ResultQueryException;
}
