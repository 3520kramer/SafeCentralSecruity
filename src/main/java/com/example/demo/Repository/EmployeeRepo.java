package com.example.demo.Repository;

/*
Starter med at impotere den tilsvarende model klasse til repository
Derefter impotere vi autowired for at få kommunikation mellem de forskellige klasser
Herefter impotere vi både beanpropertyrowmapper og rowmapper til at håndtere vores resultsets fra vores database

Til sidst importere vi jdbctemplate og repository, som skaber forbindelsen til vores database og laver klassen til
et brugbart repository

vi importere også lige list collection
 */

import com.example.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Starter med at deklarer det som værende et repository og implementere derefter vores interface


@Repository
public class EmployeeRepo implements RepoInterface{

    //Opretter et jdbctemplate objekt og autowire det

    @Autowired
    JdbcTemplate template;

    //Funktion til at hente alt fra medarbejdere i databasen, vi bruger rowmapper til at håndtere resultset
    public List<Employee> getAllEmployees(){

        String sql = "SELECT * FROM medarbejdere ORDER BY fornavn";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql, rowMapper);
    }

    //Funktion til at tilføje, bruger somsagt vores jdbctemplate objekt til at forbinde og køre update metoden
    public Employee addEmployee(Employee e) {
        String sql = "INSERT INTO medarbejdere VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, e.getFornavn(),e.getEfternavn(),e.getAnsettelsesdato(),e.getTelefon(),e.getEmail(), e.getCpr(),e.getLon(),e.getAdresse(),e.getPostnummer());
        return null;
    }

    //Funktion til at opdatere medarbejder, vi bruger int id og selve Employee klassen som parameter til at upnå dette

    public Employee updateEmployee(int id, Employee e) {
        String sql = "UPDATE medarbejdere SET fornavn = ?, efternavn = ?, ansettelsesdato = ?, telefon = ?, email = ?, cpr = ?, lon = ?, adresse = ?, postnummer = ? WHERE medarbejder_id = ?";
        template.update(sql, e.getFornavn(),e.getEfternavn(),e.getAnsettelsesdato(),e.getTelefon(),e.getEmail(), e.getCpr(),e.getLon(),e.getAdresse(),e.getPostnummer(), e.getMedarbejder_id());
        return null;
    }

    //Søge funktion til at bruge medarbejdere via. deres id til f.eks. deleteEmployee
    public Employee findEmployeeById(int id){
        String sql = "SELECT * FROM medarbejdere WHERE medarbejder_id = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee e = template.queryForObject(sql, rowMapper, id);
        return e;

    }
    //Søge funktion baseret på navn i stedet for, brugt i schedule
    public Employee findEmployeeByName(String firstName, String lastName){
        String sql = "SELECT medarbejder_id FROM medarbejdere WHERE fornavn = ? AND efternavn = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee e = template.queryForObject(sql, rowMapper, firstName, lastName);
        return e;

    }
    //Funktion til at slette medarbejder baseret på deres id

    public Boolean delete(int id) {
        String sql = "DELETE FROM medarbejdere WHERE medarbejder_id = ?";
        return template.update(sql, id) > 0;
    }

    //Funktion til at kun at få alle navnene fra medarbejder, hvor vi sammensætter fornavn og efternavn. Bruges i schedule
    public List<Employee> getAllEmployeesName(){
        String sql = "SELECT medarbejder_id, CONCAT(fornavn, ' ', efternavn) as navn\n FROM medarbejdere ORDER BY fornavn";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql, rowMapper);
    }

}
