package com.e_commerce.repository.rowmapper;

import com.e_commerce.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {


    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

        Role role = new Role();

        role.setId(rs.getInt("id"));
        role.setName(rs.getString("name"));

        return role;
    }
}
