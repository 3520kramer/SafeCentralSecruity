package com.example.demo.Repository;

/*
Starter med at impotere den tilsvarende model klasse til repository
Derefter impotere vi autowired for at få kommunikation mellem de forskellige klasser
Herefter impotere vi både beanpropertyrowmapper og rowmapper til at håndtere vores resultsets fra vores database

Til sidst importere vi jdbctemplate og repository, som skaber forbindelsen til vores database og laver klassen til
et brugbart repository

Vi indhenter også lige Calendar og List
 */

import com.example.demo.Model.Wage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

//Starter med at deklarer det som værende et repository og implementere ikke vores interface som den eneste repo klasse,
//da der ikke skal indeholde en delete wage

@Repository
public class WageRepo {

    //Opretter et jdbctemplate objekt og autowire det
    @Autowired
    JdbcTemplate template;

    /*
    Starter med at laver et kalender objekt, fra dagens dato(kan snydes ved at ændre computerens dato mm.)
    Herefter laver vi en int til "month" til at tilføje 1 til vores nuværende måned, da calendar.month starter på 0
    Så laver vi en if statement, med 2 strings, som laver en string med formattet yyyy-MM-dd, som vores database kan håndtere
    if statement bliver lavet til, hvis vores måned er under den 10. måned at tilføje et 0 foran

    Så laver vi vores select statement med concat og sum til at udregne den totale løn for den pågældende måned
     */
    public List<Wage> getWagesThisMonth(){
        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
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
        else {
            String dateFrom = cal.get(Calendar.YEAR) + "-" + month + "-01";
            String dateTo = cal.get(Calendar.YEAR) + "-" + month + "-31";
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

    //Samme metode som førhenværende, bare hvor vi selv indtaster datoerne i stedet for at de bliver genereret


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
