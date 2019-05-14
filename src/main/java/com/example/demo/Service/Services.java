package com.example.demo.Service;


import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Services {

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getAll(){
        return customerRepo.getAll();
    }
}
