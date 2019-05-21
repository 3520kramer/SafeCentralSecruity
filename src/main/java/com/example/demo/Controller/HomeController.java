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

    private String status=null;

    @Autowired
    Services services;

    @GetMapping("/")
    public String index(@ModelAttribute Login lo,Model mo) {
        mo.addAttribute("logins", lo);
        for (Login login : services.getLogin()) {
            if (login.getUsername().equals(lo.getUsername()) && login.getPassword().equals(lo.getPassword()) && login.getStatus().equals("Admin")) {
                status="Admin";
                System.out.println("getmapping index" + status);
                return "/home";
            } if (login.getUsername().equals(lo.getUsername())&& login.getPassword().equals(lo.getPassword()) && login.getStatus().equals("user")) {
                this.status="User";
                System.out.println("getmapping index" + status);
                return "/homeUser";
            }
        }


        System.out.println("getmapping index" +status);
        return "/index";
    }

    @GetMapping ("/logout")
    public String logout(){

        System.out.println("Logged out");
        status = null;
        return "redirect:/";
    }

    @GetMapping("/home")
    public String home(@ModelAttribute Login login,Model model) {
        System.out.println("getmapping home"+status);
        if (status=="Admin"){
        List<NewsFeed> newsFeedList = services.getAllNewsFeed();
        model.addAttribute("newsfeeds", newsFeedList);
        System.out.println("getmapping home" + status);
                return "/home";
            }

        return "/index";
    }

    @GetMapping("/createNewsfeed")
    public String createNewsFeed(@ModelAttribute Login lo,Model model){
      model.addAttribute("logins",lo);
      System.out.println(status);
      if(status=="Admin") {
          System.out.println("Getmapping - createnewsfeed" + status);
          return "newsfeed/createNewsfeed";
      } else if (status=="User"){
          System.out.println("Getmapping - createnewsfeed" + status);
          return "/homeUser";
      }
    return "/index";
    }

    @GetMapping("/homeUser")
    public String homeUser(@ModelAttribute Login lo,Model model){
        if (status=="User") {
            List<NewsFeed> newsFeedList = services.getAllNewsFeed();
            model.addAttribute("newsfeeds", newsFeedList);
            System.out.println("getmapping homeuser" + status);
            return "/homeUser";
        }

        return "/index";
    }

    @PostMapping("/createNewsfeed")
    public String createNewsFeed(@ModelAttribute NewsFeed newsFeed, Login lo, Model model) {
       model.addAttribute("logins", lo);
       model.addAttribute("newsfeed",newsFeed);
        if (status=="Admin") {
            services.createNewsFeed(newsFeed);
            System.out.println("postmapping createnewsfeed" + status);
            return "redirect:/home";
        }
        System.out.println("postmapping createnewsfeed" + status);
        return "/index";
    }

    @GetMapping("/delete_news/{id}")
    public String deleteNewsFeed(@PathVariable("id")int id, Model model){
        if(status=="Admin") {
            boolean deleted = services.deleteNewsFeed(id);
            if (deleted) {
                return "redirect:/home";
            } else {
                return "redirect:/home";
            }
        }
        return "index";
    }

    /*
    @GetMapping("/viewCustomer")
    public String viewCustomer() {
        if(status=="Admin") {
            return "/customer/viewCustomer";
        }else
        return "/index";
    }
    */

    @PostMapping("/viewCustomer")
    public String viewCustomers(Model model) {
        if(status=="Admin") {
            List<Customer> customerList = services.getAll();
            model.addAttribute("customers", customerList);
            return "customer/viewCustomer";
        }
        return "/index";

    }

    @GetMapping("/viewCustomer")
    public String viewCustomer(Model model) {
        if(status=="Admin") {
            List<Customer> customerList = services.getAll();
            model.addAttribute("customers", customerList);
            return "customer/viewCustomer";
        }
        return "/index";
    }

    @GetMapping("/viewLogin")
    public String viewLogin(Model model) {
        if(status=="Admin") {
            List<Login> loginList = services.getAllLogins();
            model.addAttribute("logins", loginList);
            return "login/viewLogin";
        }
        return "/index";
    }
/*
    @GetMapping("/viewEmployee")
    public String viewEmployee() {
        return "/employee/viewEmployee";
    }
*/
    @PostMapping("/viewEmployee")
    public String viewEmployees(Model model) {
        if (status=="Admin") {
            List<Employee> employeeList = services.getAllEmployees();
            model.addAttribute("employees", employeeList);
            return "employee/viewEmployee";
        }
        return "/index";
    }

    @GetMapping("/viewEmployee")
    public String viewEmployee(Model model) {
        if(status=="Admin") {
            List<Employee> employeeList = services.getAllEmployees();
            model.addAttribute("employees", employeeList);
            return "employee/viewEmployee";
        }
        return "/index";
    }

    @PostMapping("/createEmployee")
    public String createEmployee(@ModelAttribute Employee e) {
       if(status=="Admin") {
           services.addEmployee(e);
           return "redirect:/employee/viewEmployee";
       }
       return "/index";
    }


    @PostMapping("/createCustomer")
    public String create(@ModelAttribute Customer customer) {
        if(status=="Admin") {
            services.addCustomer(customer);
            return "redirect:/viewCustomer";
        }
        return "/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {

        if (status == "Admin") {
            boolean deleted = services.deleteCustomer(id);
            if (deleted) {
                return "redirect:/viewCustomer";
            } else {
                return "redirect:/viewCustomer";
            }
        }
        return "/index";
    }

    @PostMapping ("/")
    public String login2(@ModelAttribute Login lo,Model model, Model mo){
            mo.addAttribute("logins", lo);
            System.out.println("postmapping index" + status);
            services.getLogin();
            for (Login login : services.getLogin()) {
                if (login.getUsername().equals(lo.getUsername()) && login.getPassword().equals(lo.getPassword()) && login.getStatus().equals("user")) {
                    status = "User";
                    System.out.println("postmapping index" + status);

                    return "redirect:/homeUser";
                }
                if(login.getUsername().equals(lo.getUsername())&&login.getPassword().equals(lo.getPassword())&&login.getStatus().equals("Admin")){
                    status="Admin";
                    System.out.println("postmapping index" + status);
                    return "redirect:/home";
                }
            }
            return "/index";
        }



    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        if (status == "Admin") {
            boolean deleted = services.deleteEmployee(id);
            if (deleted) {
                return "redirect:/employee/viewEmployee";
            } else {
                return "redirect:/employee/viewEmployee";
            }
        }
        return "/index";
    }

    @GetMapping("/viewSchedule")
    public String viewTodaysSchedule(Model model){
        List<Schedule> scheduleList = services.getTodaysSchedule();
        model.addAttribute("schedules", scheduleList);
        return "schedule/viewTodaysSchedule";
    }

    @PostMapping("/viewScheduleDate")
    public String viewScheduleDate(@ModelAttribute ScheduleDate scheduleDate, Model model){
        model.addAttribute("dato", scheduleDate);
        List<Schedule> scheduleList = services.getOneSchedule(scheduleDate.getDate());
        model.addAttribute("schedules", scheduleList);
        return "schedule/viewScheduleDate";
    }

    @PostMapping("/viewScheduleDateFromTo")
    public String viewScheduleDateFromTo(){

        return "";
    }

    @GetMapping("/viewScheduleAll")
    public String viewScheduleAll(Model model){
        List<Schedule> scheduleList = services.getAllSchedules();
        model.addAttribute("schedules", scheduleList);
        return "schedule/viewScheduleAll";
    }

    @GetMapping("/createSchedule")
    public String createSchedule(@ModelAttribute Schedule schedule, Model model){
        if(status=="Admin") {
            List<Employee> employeeList = services.getAllEmployees();
            model.addAttribute("employees", employeeList);

            List<Customer> customerList = services.getAll();
            model.addAttribute("customers", customerList);

            return "schedule/createSchedule";
        }
        return "/index";
    }

    @PostMapping("/createSchedule")
    public String createSchedule(@ModelAttribute Schedule schedule){
        if(status=="Admin") {
            schedule.setTimetal(services.getHoursWorked(schedule.getStarttid(), schedule.getSluttid()));

            Customer c = services.findCustomerByName(schedule.getFirma_navn());
            schedule.setKunde_id(c.getKunde_id());

            Employee e = services.findEmployeeByName(schedule.getFornavn(), schedule.getEfternavn());
            schedule.setMedarbejder_id(e.getMedarbejder_id());

            services.createSchedule(schedule);
            return "redirect:/viewScheduleAll";
        }
        return "/index";
    }

    @GetMapping("/update_schedule/{schedule_id}")
    public String updateSchedule(@PathVariable("schedule_id") int schedule_id, Model model){
        Schedule s = services.findScheduleById(schedule_id);
        model.addAttribute("schedule", s);

        return "/schedule/updateSchedule";
    }

    @GetMapping("/delete_schedule/{schedule_id}")
    public String deleteSchedule(@PathVariable("schedule_id") int schedule_id){
        boolean deleted = services.deleteSchedule(schedule_id);

        return "redirect:/viewSchedule";
    }


    @PostMapping("/update_schedule")
    public String updateSchedule(@ModelAttribute Schedule schedule){
        Customer c = services.findCustomerByName(schedule.getFirma_navn());
        schedule.setKunde_id(c.getKunde_id());

        Employee e = services.findEmployeeByName(schedule.getFornavn(), schedule.getEfternavn());
        schedule.setMedarbejder_id(e.getMedarbejder_id());

        schedule.setTimetal(services.getHoursWorked(schedule.getStarttid(), schedule.getSluttid()));

        services.updateSchedule(schedule);

        return "redirect:/viewSchedule";
    }


    @GetMapping("/updateCustomer/{id}")
    public String update(@PathVariable("id") int id, Model model){
        if(status=="Admin") {
            model.addAttribute("customer", services.findCustomerById(id));
            return "customer/updateCustomer";
        }
        return "/index";

    }
    @PostMapping("/updateCustomer")
    public String update(@ModelAttribute Customer customer){
        if(status=="Admin") {
            services.updateCustomer(customer.getKunde_id(), customer);

            return "redirect:/viewCustomer";
        }
        return "/index";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") int id, Model model){
        if(status=="Admin") {
            model.addAttribute("employee", services.findEmployeeById(id));
            return "employee/updateEmployee";
        }
        return "/index";
    }
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute Employee e){
        if(status=="Admin"){
        services.updateEmployee(e.getMedarbejder_id(), e);

        return "redirect:/employee/viewEmployee";
        }
       return "/index";
    }


}