package com.e_commerce.repository.rowmapper;

import com.e_commerce.model.UserData;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataRowMapper implements RowMapper<UserData> {

    @Override
    public UserData mapRow (ResultSet rs, int rowNum) throws SQLException {

        UserData userData = new UserData();

        userData.setFiscalCode(rs.getString("fiscal_code"));
        userData.setAddress(rs.getString("address"));
        userData.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        userData.setEmail(rs.getString("email"));
        userData.setInfoPhone(rs.getLong("info_phone"));
        userData.setName(rs.getString("name"));
        userData.setSurname(rs.getString("surname"));
        userData.setUsername(rs.getString("username"));
        userData.setPassword(rs.getString("password"));

        return userData;
    }
}
