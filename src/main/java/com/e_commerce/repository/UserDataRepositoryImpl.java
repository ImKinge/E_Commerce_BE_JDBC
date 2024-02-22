package com.e_commerce.repository;

import com.e_commerce.model.UserData;
import com.e_commerce.repository.rowmapper.UserDataRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDataRepositoryImpl implements UserDataRepository{

    private static Logger logger = LoggerFactory.getLogger(UserDataRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${database.schema}")
    private String databaseSchema;

    private String table = "user";

    @Override
    public Optional<UserData> getUserDetailsByFiscalCode(String fiscalCode) {

        logger.info("Get userData details");

        String sql = "SELECT * FROM " + databaseSchema + "." + table  + " WHERE fiscal_code = ?";

        List<UserData> userDataList = jdbcTemplate.query(sql, new UserDataRowMapper(), fiscalCode);

        return userDataList.stream().findFirst();

    }

    @Override
    public Optional<UserData> findByUsername(String userName) {

        String sql = "SELECT * FROM " + databaseSchema + "." + table  + " WHERE username = ?";

        List<UserData> userDataList = jdbcTemplate.query(sql, new UserDataRowMapper(), userName);

        return userDataList.stream().findFirst();
    }
}
