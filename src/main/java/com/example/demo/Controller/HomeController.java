package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Model.NewsFeed;
import com.example.demo.Model.Owner;
import com.example.demo.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    Services services;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<NewsFeed> newsFeedList = services.getAllNewsFeed();
        model.addAttribute("newsfeeds", newsFeedList);
        return "/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteNewsFeed(@PathVariable("id")int id, Model model){
        boolean deleted = services.deleteNewsFeed(id);
        if(deleted){
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }

    @GetMapping("/viewCustomer")
    public String viewCustomer() {
        return "/customer/viewCustomer";
    }

    @PostMapping("/customer/viewCustomer")
    public String viewCustomers(Model model) {
        List<Customer> customerList = services.getAll();
        model.addAttribute("customers", customerList);
        return "customer/viewCustomer";
    }

    @GetMapping("/customer/viewCustomer")
    public String viewCustomer(Model model) {
        List<Customer> customerList = services.getAll();
        model.addAttribute("customers", customerList);
        return "customer/viewCustomer";
    }


    @GetMapping("/viewEmployee")
    public String viewEmployee() {
        return "/employee/viewEmployee";
    }

    @PostMapping("/employee/viewEmployee")
    public String viewEmployees(Model model) {
        List<Employee> employeeList = services.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employee/viewEmployee";
    }

    @GetMapping("/employee/viewEmployee")
    public String viewEmployee(Model model) {
        List<Employee> employeeList = services.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employee/viewEmployee";
    }

    @PostMapping("/createCustomer")
    public String create(@ModelAttribute Customer customer) {
        services.addCustomer(customer);
        return "redirect:/customer/viewCustomer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        boolean deleted = services.deleteCustomer(id);
        if (deleted) {
            return "redirect:/customer/viewCustomer";
        } else {
            return "redirect:/customer/viewCustomer";
        }
    }
}