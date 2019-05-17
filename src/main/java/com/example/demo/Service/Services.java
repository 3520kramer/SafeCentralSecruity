package com.example.demo.Service;


import com.example.demo.Model.*;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Repository.LoginRepo;
import com.example.demo.Repository.NewsFeedRepo;
import com.example.demo.Repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    NewsFeedRepo newsFeedRepo;
    @Autowired
    ScheduleRepo scheduleRepo;
    @Autowired
    LoginRepo loginRepo;

    public List<Customer> getAll(){
        return customerRepo.getAll();
    }

    public List<Owner> getAllEmployees(){
        return userRepo.getAllEmployees();
    }

    public List<NewsFeed> getAllNewsFeed(){
        return newsFeedRepo.getAllNewsFeed();
    }

    public Customer addCustomer (Customer c){
        return customerRepo.addCustomer(c);
    }

    public Boolean deleteNewsFeed(int id){
        return newsFeedRepo.deleteNewsFeed(id);
    }

    public Boolean deleteCustomer(int id) {
        return customerRepo.deleteCustomer(id);
    }

    public NewsFeed createNewsFeed(NewsFeed newsFeed){
        return newsFeedRepo.createNewsFeed(newsFeed);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepo.getAllSchedules();
    }
    public Owner addEmployee(Owner o){
        return userRepo.addEmployee(o);
    }

    public List<Login> getLogin(){
        return loginRepo.getLogin();
    }
    public Customer updateCustomer(int id, Customer c){
        return customerRepo.updateCustomer(id, c);
    }
    public Customer findCustomerById(int id) {
        return customerRepo.findCustomerById(id);

    }

    public Owner updateEmployee(int id, Owner o){
        return userRepo.updateEmployee(id, o);
    }

    public Owner findEmployeeById(int id) {
        return userRepo.findEmployeeById(id);
    }


    public Boolean deleteEmployee(int id){
        return userRepo.deleteEmployee(id);
    }






}

