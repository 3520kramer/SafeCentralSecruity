package com.example.demo.Repository;

import com.example.demo.Model.NewsFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsFeedRepo {

    @Autowired
    JdbcTemplate template;

    public List<NewsFeed> getAllNewsFeed(){

        String sql = "SELECT * FROM newsfeed";
        RowMapper<NewsFeed> rowMapper = new BeanPropertyRowMapper<>(NewsFeed.class);
        return template.query(sql, rowMapper);
    }

}
