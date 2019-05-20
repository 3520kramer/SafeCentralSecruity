package com.example.demo.Controller;

import com.example.demo.Model.*;
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

    private boolean status=false;


    @Autowired
    Services services;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/home")
    public String home(@ModelAttribute Login lo,Model model, Model mo) {
        List<NewsFeed> newsFeedList = services.getAllNewsFeed();
        model.addAttribute("newsfeeds", newsFeedList);
        mo.addAttribute("logins", lo);
        services.getLogin();
        for (Login login : services.getLogin()) {
            if (login.getUsername().equals(lo.getUsername()) && login.getPassword().equals(lo.getPassword()) && login.getStatus().equals("Admin")) {
                status=true;
                return "/home";
            }
        }
        return "/index";
    }

    @GetMapping("/createNewsfeed")
    public String createNewsFeed(Login lo,Model model){
      if(status=true) {
          return "newsfeed/createNewsfeed";
      } else {
          return "/index";
      }

    }

    @GetMapping("/homeUser")
    public String homeUser(@ModelAttribute Login lo,Model model,Model mo){
        List<NewsFeed> newsFeedList = services.getAllNewsFeed();
        model.addAttribute("newsfeeds", newsFeedList);
        mo.addAttribute("logins", lo);
        services.getLogin();
        for (Login login : services.getLogin()) {
            if (login.getUsername().equals(lo.getUsername()) && login.getPassword().equals(lo.getPassword()) && login.getStatus().equals("user")) {

                status=false;
                return "/homeUser";
            }
        }
        return "/index";
    }

    @PostMapping("/createNewsfeed")
    public String createNewsFeed(@ModelAttribute NewsFeed newsFeed,Login lo,Model model) {
        model.addAttribute("logins",lo);
        for (Login login : services.getLogin()) {
            if( status = true){
                return "redirect:/home";
            }
        }
       return "/index";
    }

    @GetMapping("/delete_news/{id}")
    public String deleteNewsFeed(@PathVariable("id")int id, Model model){
        boolean deleted = services.deleteNewsFeed(id);
        if(deleted){
            return "redirect:/home";
        }else{
            return "redirect:/home";
        }
    }

    /*
    @GetMapping("/viewCustomer")
    public String viewCustomer() {
        return "/customer/viewCustomer";
    }
    */

    @PostMapping("/viewCustomer")
    public String viewCustomers(Model model) {
        List<Customer> customerList = services.getAll();
        model.addAttribute("customers", customerList);
        return "customer/viewCustomer";
    }

    @GetMapping("/viewCustomer")
    public String viewCustomer(Model model) {
        List<Customer> customerList = services.getAll();
        model.addAttribute("customers", customerList);
        return "customer/viewCustomer";
    }

/*
    @GetMapping("/viewEmployee")
    public String viewEmployee() {
        return "/employee/viewEmployee";
    }
*/
    @PostMapping("/viewEmployee")
    public String viewEmployees(Model model) {
        List<Employee> employeeList = services.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employee/viewEmployee";
    }

    @GetMapping("/viewEmployee")
    public String viewEmployee(Model model) {
        List<Employee> employeeList = services.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employee/viewEmployee";
    }

    @PostMapping("/createEmployee")
    public String createEmployee(@ModelAttribute Employee e) {
        services.addEmployee(e);
        return "redirect:/employee/viewEmployee";
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

    @PostMapping("/home")
    public String login(@ModelAttribute Login la, Model model,Model mo) {
        mo.addAttribute("logins", la);
        services.getLogin();
        for (Login login : services.getLogin()) {
           if (login.getUsername().equals(la.getUsername())&& login.getPassword().equals(la.getPassword())&& login.getStatus().equals("Admin")){
               List<NewsFeed> newsFeedList = services.getAllNewsFeed();
               model.addAttribute("newsfeeds", newsFeedList);
               status=true;
               return "/home";

          } if (login.getUsername().equals(la.getUsername())&&login.getPassword().equals(la.getPassword())&&login.getStatus().equals("user")){
                List<NewsFeed> newsFeedList = services.getAllNewsFeed();
                model.addAttribute("newsfeeds", newsFeedList);
               status=false;
               return "/homeUser";
            }
        }
    return "/index";
    }

    @PostMapping ("/homeUser")
    public String login2(@ModelAttribute Login la,Model model, Model mo){
        mo.addAttribute("logins",la);
        services.getLogin();
        for(Login login: services.getLogin()){
            if(login.getUsername().equals(la.getUsername())&&login.getPassword().equals(la.getPassword())&&login.getStatus().equals("user")){
                status=false;
                return "redirect:/homeUser";
            }
        }
        return "/index";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        boolean deleted = services.deleteEmployee(id);
        if (deleted) {
            return "redirect:/employee/viewEmployee";
        } else {
            return "redirect:/employee/viewEmployee";
        }
    }

    @GetMapping("/viewSchedule")
    public String viewSchedule(Model model){
        List<Schedule> scheduleList = services.getFirstSchedule();
        model.addAttribute("schedules", scheduleList);
        return "schedule/viewSchedule";
    }

    @PostMapping("/viewScheduleDate")
    public String viewScheduleDate(@ModelAttribute ScheduleDate scheduleDate, Model model){
        model.addAttribute("dato", scheduleDate);
        List<Schedule> scheduleList = services.getOneSchedule(scheduleDate.getDate());
        model.addAttribute("schedules", scheduleList);
        return "schedule/viewScheduleDate";
    }

    @GetMapping("/viewScheduleAll")
    public String viewScheduleAll(Model model){
        List<Schedule> scheduleList = services.getAllSchedules();
        model.addAttribute("schedules", scheduleList);
        return "schedule/viewScheduleAll";
    }

    @GetMapping("/createSchedule")
    public String createSchedule(@ModelAttribute Schedule schedule, Model model){
        List<Employee> employeeList = services.getAllEmployees();
        model.addAttribute("employees", employeeList);

        List<Customer> customerList = services.getAll();
        model.addAttribute("customers", customerList);

        return "schedule/createSchedule";
    }

    @PostMapping("/createSchedule")
    public String createSchedule(@ModelAttribute Schedule schedule){
        schedule.setTimetal(services.getHoursWorked(schedule.getStarttid(), schedule.getSluttid()));

        Customer c = services.findCustomerByName(schedule.getFirma_navn());
        schedule.setKunde_id(c.getKunde_id());

        Employee e = services.findEmployeeByName(schedule.getFornavn(), schedule.getEfternavn());
        schedule.setMedarbejder_id(e.getMedarbejder_id());

        services.createSchedule(schedule);
        return "redirect:/viewSchedule";
    }

    @GetMapping("/updateCustomer/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("customer", services.findCustomerById(id));
        return "customer/updateCustomer";

    }
    @PostMapping("/updateCustomer")
    public String update(@ModelAttribute Customer customer){
        services.updateCustomer(customer.getKunde_id(), customer);

        return "redirect:/customer/viewCustomer";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") int id, Model model){
        model.addAttribute("employee", services.findEmployeeById(id));
        return "employee/updateEmployee";

    }
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute Employee e){
        services.updateEmployee(e.getMedarbejder_id(), e);

        return "redirect:/employee/viewEmployee";
    }


}