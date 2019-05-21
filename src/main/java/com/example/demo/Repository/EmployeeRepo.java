package com.example.demo.Repository;

import com.example.demo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepo implements RepoInterface{
    @Autowired
    JdbcTemplate template;


    public Employee addEmployee(Employee e) {
        String sql = "INSERT INTO medarbejdere VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, e.getFornavn(),e.getEfternavn(),e.getAnsettelsesdato(),e.getTelefon(),e.getEmail(), e.getCpr(),e.getLon(),e.getAdresse(),e.getPostnummer());
        return null;
    }

    public List<Employee> getAllEmployees(){

        String sql = "SELECT * FROM medarbejdere ORDER BY fornavn";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql, rowMapper);
    }

    public Employee updateEmployee(int id, Employee e) {
        String sql = "UPDATE medarbejdere SET fornavn = ?, efternavn = ?, ansettelsesdato = ?, telefon = ?, email = ?, cpr = ?, lon = ?, adresse = ?, postnummer = ? WHERE medarbejder_id = ?";
        template.update(sql, e.getFornavn(),e.getEfternavn(),e.getAnsettelsesdato(),e.getTelefon(),e.getEmail(), e.getCpr(),e.getLon(),e.getAdresse(),e.getPostnummer(), e.getMedarbejder_id());
        return null;
    }

    public Employee findEmployeeById(int id){
        String sql = "SELECT * FROM medarbejdere WHERE medarbejder_id = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee e = template.queryForObject(sql, rowMapper, id);
        return e;

    }

    public Employee findEmployeeByName(String firstName, String lastName){
        String sql = "SELECT medarbejder_id FROM medarbejdere WHERE fornavn = ? AND efternavn = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee e = template.queryForObject(sql, rowMapper, firstName, lastName);
        return e;

    }

    public Boolean delete(int id) {
        String sql = "DELETE FROM medarbejdere WHERE medarbejder_id = ?";
        return template.update(sql, id) > 0;
    }

    public List<Employee> getAllEmployeesName(){
        String sql = "SELECT CONCAT(fornavn, ' ', efternavn) as navn\n FROM medarbejdere";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql, rowMapper);
    }

}
