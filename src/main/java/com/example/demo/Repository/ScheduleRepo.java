package com.example.demo.Repository;

import com.example.demo.Model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleRepo {

    @Autowired
    JdbcTemplate template;

    public List<Schedule> getAllSchedules(){
        String sql = "SELECT fornavn, efternavn, starttid, sluttid, dato, firma_navn, k.adresse, bydel, k.postnummer\n" +
                "vagtplan_id, medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY vagtplan_id;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper);
    }
}
