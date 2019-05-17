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
        String sql = "INSERT INTO medarbejdere VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, o.getFornavn(),o.getEfternavn(),o.getAnsettelsesdato(),o.getTelefon(),o.getEmail(), o.getCpr(),o.getLon(),o.getAdresse(),o.getPostnummer());
        return null;
    }

    public List<Owner> getAllEmployees(){

        String sql = "SELECT * FROM medarbejdere ORDER BY fornavn";
        RowMapper<Owner> rowMapper = new BeanPropertyRowMapper<>(Owner.class);
        return template.query(sql, rowMapper);
    }

    public Owner updateEmployee(int id, Owner o) {
        String sql = "UPDATE medarbejdere SET fornavn = ?, efternavn = ?, ansettelsesdato = ?, telefon = ?, email = ?, cpr = ?, lon = ?, adresse = ?, postnummer = ? WHERE medarbejder_id = ?";
        template.update(sql, o.getFornavn(),o.getEfternavn(),o.getAnsettelsesdato(),o.getTelefon(),o.getEmail(), o.getCpr(),o.getLon(),o.getAdresse(),o.getPostnummer(), o.getMedarbejder_id());
        return null;
    }

    public Owner findEmployeeById(int id){
        String sql = "SELECT * FROM medarbejdere WHERE medarbejder_id = ?";
        RowMapper<Owner> rowMapper = new BeanPropertyRowMapper<>(Owner.class);
        Owner o = template.queryForObject(sql, rowMapper, id);
        return o;

    }

    public Boolean deleteEmployee(int id) {
        String sql = "DELETE FROM medarbejdere WHERE medarbejder_id = ?";
        return template.update(sql, id) > 0;
    }

}
