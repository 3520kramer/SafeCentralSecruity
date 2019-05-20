package com.example.demo.Repository;

import com.example.demo.Model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ScheduleRepo {

    @Autowired
    JdbcTemplate template;

    public List<Schedule> getAllSchedules(){
        String sql = "SELECT fornavn, efternavn, starttid, sluttid, timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
                "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY dato DESC;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper);
    }

    public List<Schedule> getFirstSchedule(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = new Date();
        String date = dateFormat.format(dateObj);

        String sql = "SELECT fornavn, efternavn, starttid, sluttid, timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
                "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "WHERE dato = ?\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY starttid;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper, date);
    }

    public List<Schedule> getOneSchedule(String date){
        String sql = "SELECT fornavn, efternavn, starttid, sluttid, timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
                "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "WHERE dato = ?\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY starttid;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper, date);
    }

    public Schedule createSchedule(Schedule s){
        String sql = "INSERT INTO vagtplan VALUES(null, ?, ?, ?, ?, ?, ?)";
        template.update(sql, s.getStarttid(), s.getSluttid(), s.getTimetal(), s.getDato(), s.getMedarbejder_id(), s.getKunde_id());
        return null;
    }

}
