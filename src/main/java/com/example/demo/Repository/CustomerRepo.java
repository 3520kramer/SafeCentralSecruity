package com.example.demo.Repository;


import com.example.demo.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public List<Customer> getAll(){

        String sql = "SELECT * FROM kunder";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }

    public Customer addCustomer(Customer c) {
        String sql = "INSERT INTO kunder VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getFirma_navn(), c.getKontaktperson(), c.getTelefon(), c.getEmail(), c.getCVR(), c.getAddresse(), c.getPostnummer());
        return null;
    }

    public Boolean deleteCustomer(int id) {

        String sql = "DELETE FROM kunder WHERE id = ?";

        return template.update(sql, id) > 0;
    }

}
