package com.example.demo.Repository;

import com.example.demo.Model.Login;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class LoginRepo {

    @Autowired
    JdbcTemplate template;


    public Login compareInfo(String username, String password){
        String sql ="SELECT * FROM brugere WHERE username=? AND password =?";
        RowMapper<Login> rowMapper = new BeanPropertyRowMapper<>(Login.class);
        Login l =template.queryForObject(sql,rowMapper,username,password);
        return l;



    }



}
