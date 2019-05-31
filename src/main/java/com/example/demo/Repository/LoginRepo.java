package com.example.demo.Repository;

/*
Starter med at impotere den tilsvarende model klasse til repository
Derefter impotere vi autowired for at få kommunikation mellem de forskellige klasser
Herefter impotere vi både beanpropertyrowmapper og rowmapper til at håndtere vores resultsets fra vores database

Til sidst importere vi jdbctemplate og repository, som skaber forbindelsen til vores database og laver klassen til
et brugbart repository

vi importere også lige list collection
 */

import com.example.demo.Model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;

//Starter med at deklarer det som værende et repository og implementere derefter vores interface

@Repository
public class LoginRepo {

    //Opretter et jdbctemplate objekt og autowire det

    @Autowired
    JdbcTemplate template;

    //Funktion til at hente alt fra brugere i databasen, vi bruger rowmapper til at håndtere resultset

    public List<Login> getLogin() {
        String sql = "SELECT * FROM brugere";
        RowMapper<Login> rowMapper = new BeanPropertyRowMapper<>(Login.class);

        return template.query(sql, rowMapper);

    }
    //Funktion til at hente alle brugere, undtagen den allerførste, hvor bruger_id = 1, dette er for at have en failsafe
    //sådan så ejeren ikke kan komme til at slette alle brugere der har adgang til siden. Vi bruger rowmapper til at håndtere resultset

    public List<Login> getAll() {
        String sql = "SELECT * FROM brugere WHERE bruger_id > 1";
        RowMapper<Login> rowMapper = new BeanPropertyRowMapper<>(Login.class);

        return template.query(sql, rowMapper);

    }
    //Funktion til at tilføje, bruger somsagt vores jdbctemplate objekt til at forbinde og køre update metoden
    //  If/else til at sikre sig at der altid ville være en status på oprettede brugere

    public Login addLogin(Login l) {
        String sql1 = "INSERT INTO brugere VALUES (DEFAULT, ?, ?, ?)";

        if (l.getStatus().equals(null)) {
            String sql = "INSERT INTO brugere VALUES (DEFAULT, ?, ?, DEFAULT)";
            template.update(sql, l.getUsername(), l.getPassword());
            System.out.println(l.getStatus());
        } else {
            template.update(sql1, l.getUsername(), l.getPassword(), l.getStatus());
            System.out.println(l.getStatus());
            return null;
        }
        return null;
    }
    //Funktion til at slette logins

    public Boolean delete(int id) {

        String sql = "DELETE FROM brugere WHERE bruger_id = ?";

        return template.update(sql, id) > 0;
    }

    //Funktion til at opdatere logins, vi bruger int id og selve Login klassen som parameter til at upnå dette

    public Login updateLogin(int id, Login l){
        String sql = "UPDATE brugere SET username = ?, password = ?, status = ? WHERE bruger_id = ?";
        template.update(sql, l.getUsername(), l.getPassword(), l.getStatus(), l.getBruger_id());
        return null;

    }

    //Søge funktion til at bruge logins via. deres id til f.eks. delete

    public Login findLoginById(int id){
        String sql = "SELECT * FROM brugere WHERE bruger_id = ?";
        RowMapper<Login> rowMapper = new BeanPropertyRowMapper<>(Login.class);
        Login l = template.queryForObject(sql, rowMapper, id);
        return l;
    }







}

