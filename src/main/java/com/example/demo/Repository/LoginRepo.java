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

    public Login addLogin(Login l) {
        String sql = "INSERT INTO brugere VALUES (DEFAULT, ?, ?, ?)";
        template.update(sql, l.getUsername(), l.getUsername(), l.getStatus());
        return null;
    }

    public Boolean delete(int id) {

        String sql = "DELETE FROM brugere WHERE bruger_id = ?";

        return template.update(sql, id) > 0;
    }
    public Login updateLogin(int id, Login l){
        String sql = "UPDATE brugere SET username = ?, password = ?, status = ? WHERE bruger_id = ?";
        template.update(sql, l.getUsername(), l.getPassword(), l.getStatus(), l.getBruger_id());
        return null;

    }
    public Login findLoginById(int id){
        String sql = "SELECT * FROM brugere WHERE bruger_id = ?";
        RowMapper<Login> rowMapper = new BeanPropertyRowMapper<>(Login.class);
        Login l = template.queryForObject(sql, rowMapper, id);
        return l;
    }

    /*
    public Customer findCustomerByName(String firmaNavn){
        String sql = "SELECT kunde_id FROM kunder WHERE firma_navn = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, rowMapper, firmaNavn);
        return c;
    }
    */





}

