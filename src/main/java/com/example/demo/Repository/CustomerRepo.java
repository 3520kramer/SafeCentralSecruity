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
        template.update(sql, c.getFirma_navn(), c.getKontaktperson(), c.getTelefon(), c.getEmail(), c.getCVR(), c.getAdresse(), c.getPostnummer());
        return null;
    }

    public Boolean deleteCustomer(int id) {

        String sql = "DELETE FROM kunder WHERE kunde_id = ?";

        return template.update(sql, id) > 0;
    }
    public Customer updateCustomer(int id, Customer c){
        String sql = "UPDATE kunder SET firma_navn = ?, kontaktperson = ?, telefon = ?, email = ?, CVR = ?, adresse = ?, postnummer = ? WHERE kunde_id = ?";
        template.update(sql, c.getFirma_navn(), c.getKontaktperson(), c.getTelefon(), c.getEmail(), c.getCVR(), c.getAdresse(), c.getPostnummer(), c.getKunde_id());
        return null;

    }
    public Customer findCustomerById(int id){
        String sql = "SELECT * FROM kunder WHERE kunde_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, rowMapper, id);
        return c;

    }




}
