package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    @Autowired
    JdbcTemplate template;

    public List<User> getAllEmployees(){

        String sql = "SELECT * FROM medarbejder";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return template.query(sql, rowMapper);
    }

}
