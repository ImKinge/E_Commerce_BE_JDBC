package com.e_commerce.repository;

import com.e_commerce.model.UserData;

import java.util.Optional;

public interface UserDataRepository {

    UserData getUserDetailsByFiscalCode (String fiscalCode);

    Optional<UserData> findByUsername (String userName);

}
