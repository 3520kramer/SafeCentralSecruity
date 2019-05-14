package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController{


    @Autowired
    Services services;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/home")
    public String home(){
        return "/home";
    }

    @GetMapping("/viewCustomer")
    public String viewCustomer(){
        return "/customer/viewCustomer";
    }


    @PostMapping("/customer/viewCustomer")
    public String viewCustomers(Model model) {
        List<Customer> customerList = services.getAll();
        model.addAttribute("customers", customerList);
        return "customer/viewCustomer";
    }

}