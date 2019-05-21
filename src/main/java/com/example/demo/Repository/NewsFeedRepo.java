package com.example.demo.Repository;

import com.example.demo.Model.NewsFeed;
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
public class NewsFeedRepo {

    @Autowired
    JdbcTemplate template;

    public List<NewsFeed> getAllNewsFeed(){
        String sql = "SELECT * FROM newsfeed ORDER BY newsfeed_id DESC";
        RowMapper<NewsFeed> rowMapper = new BeanPropertyRowMapper<>(NewsFeed.class);
        return template.query(sql, rowMapper);
    }

    public Boolean deleteNewsFeed(int id){
        String sql = "DELETE FROM newsfeed WHERE newsfeed_id = ?";
        return template.update(sql, id) > 0;
    }

    public NewsFeed createNewsFeed(NewsFeed newsFeed){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        Date date = new Date();

        String dato = dateFormat.format(date);
        String tid = timeFormat.format(date);

        String sql = "INSERT INTO newsfeed VALUES (null, ?, ?, ?)";
        template.update(sql, newsFeed.getOpslag(), dato, tid);
        return null;
    }
}