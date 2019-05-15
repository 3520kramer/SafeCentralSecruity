package com.example.demo.Service;


import com.example.demo.Model.Customer;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Services {

    @Autowired
    CustomerRepo customerRepo;
    EmployeeRepo employeeRepo;

    public List<Customer> getAll(){
        return customerRepo.getAll();
    }


    public List<Employee> getAllEmployees(){
        return employeeRepo.getAllEmployees();
    }









}

