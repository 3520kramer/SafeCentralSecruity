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
        String sql = "";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper);
    }
}
