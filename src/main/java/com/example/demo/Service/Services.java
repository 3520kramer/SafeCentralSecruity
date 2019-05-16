package com.example.demo.Service;


import com.example.demo.Model.Customer;
import com.example.demo.Model.Owner;
import com.example.demo.Model.NewsFeed;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.OwnerRepo;
import com.example.demo.Repository.NewsFeedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    OwnerRepo ownerRepo;
    @Autowired
    NewsFeedRepo newsFeedRepo;

    public List<Customer> getAll(){
        return customerRepo.getAll();
    }


    public List<Owner> getAllEmployees(){
        return ownerRepo.getAllEmployees();
    }

    public Customer addCustomer (Customer c){
        return customerRepo.addCustomer(c);
    }

    public List<NewsFeed> getAllNewsFeed(){
        return newsFeedRepo.getAllNewsFeed();
    }

    public Boolean deleteNewsFeed(int id){
        return newsFeedRepo.deleteNewsFeed(id);
    }

    public Boolean deleteCustomer(int id) {
        return customerRepo.deleteCustomer(id);
    }










}

