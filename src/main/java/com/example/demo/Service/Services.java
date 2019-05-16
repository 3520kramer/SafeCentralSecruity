package com.example.demo.Service;


import com.example.demo.Model.Customer;
import com.example.demo.Model.Employee;
import com.example.demo.Model.NewsFeed;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.EmployeeRepo;
import com.example.demo.Repository.NewsFeedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    NewsFeedRepo newsFeedRepo;

    public List<Customer> getAll(){
        return customerRepo.getAll();
    }


    public List<Employee> getAllEmployees(){
        return employeeRepo.getAllEmployees();
    }

    public List<NewsFeed> getAllNewsFeed(){
        return newsFeedRepo.getAllNewsFeed();
    }

    public Boolean deleteNewsFeed(int id){
        return newsFeedRepo.deleteNewsFeed(id);
    }











}

