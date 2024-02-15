package com.e_commerce.service;

import com.e_commerce.dto.UserDataDto;
import com.e_commerce.exception.ResultQueryException;

public interface UserService {

    UserDataDto getUserDetailsByFiscalCode (String fiscalCode) throws ResultQueryException;

    UserDataDto findUserByUsername (String username) throws ResultQueryException;
}
