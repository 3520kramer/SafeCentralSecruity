package com.example.demo.Repository;

import com.example.demo.Model.Owner;
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


    public Owner addEmployee(Owner o) {
        String sql = "INSERT INTO medarbejder VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, o.getFornavn(),o.getEfternavn(),o.getAnsettelsesdato(),o.getTelefon(),o.getEmail(), o.getCpr(),o.getLon(),o.getAddresse(),o.getPostnummer());
        return null;
    }

    public List<Owner> getAllEmployees(){

        String sql = "SELECT * FROM medarbejder ORDER BY fornavn";
        RowMapper<Owner> rowMapper = new BeanPropertyRowMapper<>(Owner.class);
        return template.query(sql, rowMapper);
    }

    public Owner updateEmployee(int id, Owner o) {
        String sql = "UPDATE medarbejder SET fornavn = ?, efternavn = ?, ansettelsesdato = ?, telefon = ?, email = ?, cpr = ?, lon = ?, addresse = ?, postnummer = ? WHERE id = ?";
        template.update(sql, o.getFornavn(),o.getEfternavn(),o.getAnsettelsesdato(),o.getTelefon(),o.getEmail(), o.getCpr(),o.getLon(),o.getAddresse(),o.getPostnummer(), o.getId());
        return null;
    }

    public Owner findEmployeeById(int id){
        String sql = "SELECT * FROM kunder WHERE id = ?";
        RowMapper<Owner> rowMapper = new BeanPropertyRowMapper<>(Owner.class);
        Owner o = template.queryForObject(sql, rowMapper, id);
        return o;

    }

    public Boolean deleteEmployee(int id) {
        String sql = "DELETE FROM medarbejder WHERE id = ?";
        return template.update(sql, id) > 0;
    }

}
