package com.e_commerce.repository;

import com.e_commerce.model.Role;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository {

    List<Role> findRoleByFiscalCode (String fiscalCode);
}
