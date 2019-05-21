package com.example.demo.Repository;

import com.example.demo.Model.Login;
import com.example.demo.Model.User;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class LoginRepo {

    @Autowired
    JdbcTemplate template;


    public List<Login> getLogin() {
        String sql = "SELECT * FROM brugere";
        RowMapper<Login> rowMapper = new BeanPropertyRowMapper<>(Login.class);

        return template.query(sql, rowMapper);

    }

    public List<Login> getAll() {
        String sql = "SELECT * FROM brugere WHERE bruger_id > 1";
        RowMapper<Login> rowMapper = new BeanPropertyRowMapper<>(Login.class);

        return template.query(sql, rowMapper);

    }






}

