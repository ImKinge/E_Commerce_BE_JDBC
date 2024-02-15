package com.e_commerce.repository.rowmapper;

import com.e_commerce.model.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRowMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserRole userRole = new UserRole();

        userRole.setRoleId(rs.getInt("role_id"));
        userRole.setFiscalCode(rs.getString("customer_id"));

        return userRole;
    }
}
