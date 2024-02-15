package com.e_commerce.repository;

import com.e_commerce.model.Role;
import com.e_commerce.model.UserRole;
import com.e_commerce.repository.rowmapper.RoleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${database.schema}")
    private String databaseSchema;


    private String tableRoles = "roles";

    private String tableUserRoles = "user_roles";

    @Override
    public List<Role> findRoleByFiscalCode(String fiscalCode) {

        String sql = "SELECT roles.id, roles.name FROM " + databaseSchema + "." + tableUserRoles  + " INNER JOIN roles ON user_roles.role_id = roles.id" + " WHERE user_roles.customer_id = ?";

        List<Role> roleList = jdbcTemplate.query(sql, new RoleRowMapper(), fiscalCode);

        return roleList;
    }
}
