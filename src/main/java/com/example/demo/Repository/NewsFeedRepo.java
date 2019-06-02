package com.example.demo.Repository;

/*
Starter med at impotere den tilsvarende model klasse til repository
Derefter impotere vi autowired for at få kommunikation mellem de forskellige klasser
Herefter impotere vi både beanpropertyrowmapper og rowmapper til at håndtere vores resultsets fra vores database

Til sidst importere vi jdbctemplate og repository, som skaber forbindelsen til vores database og laver klassen til
et brugbart repository

Vi indhenter også lige DateFormat, SimpleDateFormat, Date og List
Dette gør vi for at få et snapshot af tidspunktet et nyhedsopslag er oprettet
 */

import com.example.demo.Model.NewsFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//Starter med at deklarer det som værende et repository og implementere derefter vores interface

@Repository
public class NewsFeedRepo implements RepoInterface {
    // Ansvarlige: Oliver, Kasper

    //Opretter et jdbctemplate objekt og autowire det

    @Autowired
    JdbcTemplate template;

    //Funktion til at hente alt fra newsfeed i databasen, vi bruger rowmapper til at håndtere resultset

    public List<NewsFeed> getAllNewsFeed(){
        String sql = "SELECT * FROM newsfeed ORDER BY newsfeed_id DESC";
        RowMapper<NewsFeed> rowMapper = new BeanPropertyRowMapper<>(NewsFeed.class);
        return template.query(sql, rowMapper);
    }
    //Funktion til at slette nyhedsopslag

    public Boolean delete(int id){
        String sql = "DELETE FROM newsfeed WHERE newsfeed_id = ?";
        return template.update(sql, id) > 0;
    }

    /*
    Funktion til at tilføje, bruger somsagt vores jdbctemplate objekt til at forbinde og køre update metoden
    Vi starter dog med at oprette vores dateformat objekt med formatet yyyy-MM-dd, som er den som MySQL bruger som standard
    Dernæst opretter vi også vores timeformat til HH:mm:ss, som igen er MySQL´s standard.
    Så opretter vi et date objekt, og opretter 2 strings med den, til at oprette vores tid string og dato string, som vi bruger i vores mysql kode

    */
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