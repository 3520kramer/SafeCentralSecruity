package com.example.demo.Repository;

/*
Starter med at impotere den tilsvarende model klasse til repository
Derefter impotere vi autowired for at få kommunikation mellem de forskellige klasser
Herefter impotere vi både beanpropertyrowmapper og rowmapper til at håndtere vores resultsets fra vores database

Til sidst importere vi jdbctemplate og repository, som skaber forbindelsen til vores database og laver klassen til
et brugbart repository

vi importere også lige list collection
 */
import com.example.demo.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Starter med at deklarer det som værende et repository og implementere derefter vores interface

@Repository
public class CustomerRepo implements RepoInterface {
    // Ansvarlige: Kasper

    //Opretter et jdbctemplate objekt og autowire det

    @Autowired
    JdbcTemplate template;

    //Funktion til at hente alt fra kunder i databasen, vi bruger rowmapper til at håndtere resultset
    public List<Customer> getAll(){

        String sql = "SELECT * FROM kunder";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }

    //Funktion til at tilføje, bruger somsagt vores jdbctemplate objekt til at forbinde og køre update metoden
    public Customer addCustomer(Customer c) {
        String sql = "INSERT INTO kunder VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getFirma_navn(), c.getKontaktperson(), c.getTelefon(), c.getEmail(), c.getCVR(), c.getAdresse(), c.getPostnummer());
        return null;
    }

    //Funktion til at slette kunder
    public Boolean delete(int id) {

        String sql = "DELETE FROM kunder WHERE kunde_id = ?";

        return template.update(sql, id) > 0;
    }

    //Funktion til at opdatere kunder, vi bruger int id og selve Customer klassen som parameter til at upnå dette
    public Customer updateCustomer(int id, Customer c){
        String sql = "UPDATE kunder SET firma_navn = ?, kontaktperson = ?, telefon = ?, email = ?, CVR = ?, adresse = ?, postnummer = ? WHERE kunde_id = ?";
        template.update(sql, c.getFirma_navn(), c.getKontaktperson(), c.getTelefon(), c.getEmail(), c.getCVR(), c.getAdresse(), c.getPostnummer(), c.getKunde_id());
        return null;

    }
    //Søge funktion til at bruge kunder via. deres id til f.eks. delete
    public Customer findCustomerById(int id){
        String sql = "SELECT * FROM kunder WHERE kunde_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, rowMapper, id);
        return c;
    }
    //Søge funktion baseret på navn i stedet for, brugt i schedule
    public Customer findCustomerByName(String firmaNavn){
        String sql = "SELECT kunde_id FROM kunder WHERE firma_navn = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, rowMapper, firmaNavn);
        return c;
    }


}
