package com.example.demo.Repository;

import com.example.demo.Model.Wage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.MONTH;

@Repository
public class WageRepo {
    @Autowired
    JdbcTemplate template;

    public List<Wage> getWagesThisMonth(){
        Calendar cal = Calendar.getInstance();

        int month = cal.get(MONTH) + 1;
        String dateFrom = cal.get(Calendar.YEAR) + "-0" + month + "-01";
        String dateTo = cal.get(Calendar.YEAR) + "-0" + month + "-31";

        String sql = "SELECT medarbejder_id, CONCAT(fornavn, ' ', efternavn) AS navn, cpr, lon, SUM(timetal) AS timetal, SUM(timetal * lon) AS totallon\n" +
                "FROM medarbejdere\n" +
                "JOIN vagtplan\n" +
                "ON medarbejder_id = medarbejder_id_fk\n" +
                "WHERE dato >= ? AND dato <= ?\n" +
                "GROUP BY medarbejder_id\n" +
                "ORDER BY fornavn, efternavn";
        RowMapper<Wage> rowMapper = new BeanPropertyRowMapper<>(Wage.class);
        return template.query(sql, rowMapper, dateFrom, dateTo);
    }

    public List<Wage> getWagesDateFromTo(String dateFrom, String dateTo){
        String sql = "SELECT medarbejder_id, CONCAT(fornavn, ' ', efternavn) AS navn, cpr, lon, SUM(timetal) AS timetal, SUM(timetal * lon) AS totallon\n" +
                "FROM medarbejdere\n" +
                "JOIN vagtplan\n" +
                "ON medarbejder_id = medarbejder_id_fk\n" +
                "WHERE dato >= ? AND dato <= ?\n" +
                "GROUP BY medarbejder_id\n" +
                "ORDER BY fornavn, efternavn";
        RowMapper<Wage> rowMapper = new BeanPropertyRowMapper<>(Wage.class);
        return template.query(sql, rowMapper, dateFrom, dateTo);
    }
}
