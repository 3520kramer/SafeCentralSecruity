//Gør Controller package en del af com.example.demo package

package com.example.demo.Controller;

// Importere alle model klasserne og service klassen
import com.example.demo.Model.*;
import com.example.demo.Service.Services;

/* Importere springframework til at få vores autowire, controller
post/getmapping, pathvariable og modelattribute til at fungere
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// importere java list som bruges senere til når vi bruger listerne
import java.util.List;

//Deklare at dette er vores controller
@Controller
public class HomeController {

    // String som bruges til login
    private String status = null;

    //Autowires services klassen så der er forbindelse mellem homecontroller og services klasserne
    @Autowired
    Services services;


    /**************************
     **************************
     *-Index, home og logout -*
     **************************
     **************************
     ___________________________________________________________________________*/

/*
Index siden, som bruger Login klassen som parameter
til at gå alle vores logins igennem med et enhanced for-loop, hvor den sammenligner
de indtastede værdier med vores allerede eksiterende værdier. Derefter ændre
den status til den tilsvarende bruger. Hvis den ikke finder en bruger med
matchende værdier med databasen, så smider den dig tilbage til index siden.
 */
    @GetMapping("/")
    public String index(Login lo, Model mo) {
        for (Login login : services.getLogin()) {
            if (login.getUsername().equals(lo.getUsername()) && login.getPassword().equals(lo.getPassword()) && login.getStatus().equalsIgnoreCase("Admin")) {
                status="Admin";
                System.out.println("getmapping index" + status);
                return "/home";
            } if (login.getUsername().equals(lo.getUsername())&& login.getPassword().equals(lo.getPassword()) && login.getStatus().equalsIgnoreCase("user")) {
                this.status="User";
                System.out.println("getmapping index" + status);
                return "/homeUser";
            }
        }


        System.out.println("getmapping index" +status);
        return "/index";
    }

    //Log ud til at ændre vores status tilbage til null og redirect til index siden

    @GetMapping ("/logout")
    public String logout(){

        System.out.println("Logged out");
        status = null;
        return "redirect:/";
    }

    /*
    Getmapping for home, hvor vi opretter en list til at hente alt fra newsfeed
    via. spring framework model.addAttribute

    Samme er ens for storset alle andre getmapping bare med forskellige tables og html sider der bliver hentet
    */

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("getmapping home"+status);
        if (status.equalsIgnoreCase("Admin")){
        List<NewsFeed> newsFeedList = services.getAllNewsFeed();
        model.addAttribute("newsfeeds", newsFeedList);
        System.out.println("getmapping home" + status);
                return "/home";
            }

        return "/index";
    }


    @GetMapping("/homeUser")
    public String homeUser(Model model){
        if (status.equalsIgnoreCase("user")) {
            List<NewsFeed> newsFeedList = services.getAllNewsFeed();
            model.addAttribute("newsfeeds", newsFeedList);
            System.out.println("getmapping homeuser" + status);
            return "/homeUser";
        }

        return "/index";
    }

    /*
    Vi bruger en postmapping hvor vi bruger Login som parameter til at bruge et enhanced for-loop
    som går igennem vores Logins fra databasen og sammenligner med de indtastede værdier i gennem vores "lo" objekt.
    */


    @PostMapping ("/")
    public String login2(Login lo){

        for (Login login : services.getLogin()) {
            if (login.getUsername().equals(lo.getUsername()) && login.getPassword().equals(lo.getPassword()) && login.getStatus().equalsIgnoreCase("user")) {
                status = "User";
                System.out.println("postmapping index" + status);

                return "redirect:/homeUser";
            }
            if(login.getUsername().equals(lo.getUsername())&&login.getPassword().equals(lo.getPassword())&&login.getStatus().equalsIgnoreCase("Admin")){
                status="Admin";
                System.out.println("postmapping index" + status);
                return "redirect:/home";
            }
        }
        return "/index";
    }





    /**************************
     **************************
     ****---- Newsfeed ----****
     **************************
     **************************
     ___________________________________________________________________________*/

/*



 */


    @GetMapping("/createNewsfeed")
    public String createNewsFeed(){
      System.out.println(status);
      if(status.equalsIgnoreCase("Admin")) {
          System.out.println("Getmapping - createnewsfeed" + status);
          return "newsfeed/createNewsfeed";
      } else if (status=="User"){
          System.out.println("Getmapping - createnewsfeed" + status);
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












    /**************************
     **************************
     ****---- Customer ----****
     **************************
     **************************
     ___________________________________________________________________________*/



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






    /**************************
     **************************
     ****---- Employee ----****
     **************************
     **************************
     ___________________________________________________________________________*/



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
           return "redirect:/viewEmployee";
       }
       return "/index";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        if (status == "Admin") {
            boolean deleted = services.deleteEmployee(id);
            if (deleted) {
                return "redirect:/viewEmployee";
            } else {
                return "redirect:/viewEmployee";
            }
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

            return "redirect:/viewEmployee";
        }
        return "/index";
    }













    /**************************
     **************************
     ****---- Schedule ----****
     **************************
     **************************
     ___________________________________________________________________________*/





    @GetMapping("/viewSchedule")
    public String viewTodaysSchedule(Model model){
        if(status =="Admin"){
            List<Schedule> scheduleList = services.getTodaysSchedule();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status == "User"){
            List<Schedule> scheduleList = services.getTodaysSchedule();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "redirect:/index";
        }
    }

    @PostMapping("/viewScheduleDate")
    public String viewScheduleDate(@ModelAttribute DateFromTo dateFromTo, Model model){
        if(status =="Admin"){
            List<Schedule> scheduleList = services.getOneSchedule(dateFromTo.getDate());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status == "User"){
            List<Schedule> scheduleList = services.getOneSchedule(dateFromTo.getDate());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "redirect:/index";
        }
    }

    @PostMapping("/viewScheduleDateFromTo")
    public String viewScheduleDateFromTo(@ModelAttribute DateFromTo dateFromTo, Model model){
        if(status =="Admin"){
            List<Schedule> scheduleList = services.getScheduleDateFromTo(dateFromTo.getDate(), dateFromTo.getDateTo());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status == "User"){
            List<Schedule> scheduleList = services.getScheduleDateFromTo(dateFromTo.getDate(), dateFromTo.getDateTo());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "/index";
        }
    }

    @GetMapping("/viewScheduleAll")
    public String viewScheduleAll(Model model){
        if(status =="Admin"){
            List<Schedule> scheduleList = services.getAllSchedules();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status == "User"){
            List<Schedule> scheduleList = services.getAllSchedules();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "/index";
        }
    }

    @GetMapping("/createSchedule")
    public String createSchedule(@ModelAttribute Schedule schedule, Model model){
        if(status=="Admin") {
            List<Employee> employeeList = services.getAllEmployeesNames();
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

            String[] splitName = schedule.getNavn().split("\\s+");
            schedule.setFornavn(splitName[0]);
            schedule.setEfternavn(splitName[1]);

            Employee e = services.findEmployeeByName(schedule.getFornavn(), schedule.getEfternavn());
            schedule.setMedarbejder_id(e.getMedarbejder_id());

            services.createSchedule(schedule);
            return "redirect:/viewScheduleAll";
        }
        return "/index";
    }

    @GetMapping("/update_schedule/{schedule_id}")
    public String updateSchedule(@PathVariable("schedule_id") int schedule_id, Model model){
        if(status=="Admin") {
            Schedule s = services.findScheduleById(schedule_id);
            model.addAttribute("schedule", s);

            List<Employee> employeeList = services.getAllEmployeesNames();
            model.addAttribute("employees", employeeList);

            List<Customer> customerList = services.getAll();
            model.addAttribute("customers", customerList);

            return "/schedule/updateSchedule";
        }
        return "index";
    }

    @GetMapping("/delete_schedule/{schedule_id}")
    public String deleteSchedule(@PathVariable("schedule_id") int schedule_id){
        if(status=="Admin") {
            boolean deleted = services.deleteSchedule(schedule_id);

            return "redirect:/viewScheduleAll";
        }
        return "index";
    }


    @PostMapping("/update_schedule")
    public String updateSchedule(@ModelAttribute Schedule schedule){
        if(status=="Admin") {
            String[] splitName = schedule.getNavn().split("\\s+");
            schedule.setFornavn(splitName[0]);
            schedule.setEfternavn(splitName[1]);

            Customer c = services.findCustomerByName(schedule.getFirma_navn());
            schedule.setKunde_id(c.getKunde_id());

            Employee e = services.findEmployeeByName(schedule.getFornavn(), schedule.getEfternavn());
            schedule.setMedarbejder_id(e.getMedarbejder_id());

            schedule.setTimetal(services.getHoursWorked(schedule.getStarttid(), schedule.getSluttid()));

            services.updateSchedule(schedule);

            return "redirect:/viewScheduleAll";
        }
        return "/index";
    }











    /**************************
     **************************
     ****----   Login  ----****
     **************************
     **************************
     ___________________________________________________________________________*/



    @GetMapping("/viewLogin")
    public String viewLogin(Model model) {
        if(status=="Admin") {
            List<Login> loginList = services.getAllLogins();
            model.addAttribute("logins", loginList);
            return "login/viewLogin";
        }
        return "/index";
    }

    @PostMapping("/viewLogin")
    public String viewLogins(Model model) {
        if(status=="Admin") {
            List<Login> loginList = services.getAllLogins();
            model.addAttribute("logins", loginList);
            return "login/viewLogin";
        }
        return "/index";

    }

    @PostMapping("/createLogin")
    public String create(@ModelAttribute Login login) {
        if(status=="Admin") {
            services.addLogin(login);
            System.out.println("Login runs");
            return "redirect:/viewLogin";

        }
        return "/index";
    }

    @GetMapping("/deleteLogin/{id}")
    public String deleteLogin(@PathVariable("id") int id) {

        if (status == "Admin") {
            boolean deleted = services.deleteLogin(id);
            if (deleted) {
                return "redirect:/viewLogin";
            } else {
                return "redirect:/viewLogin";
            }
        }
        return "/index";
    }



    @GetMapping("/updateLogin/{id}")
    public String updateLogin(@PathVariable("id") int id, Model model){
        if(status=="Admin") {
            model.addAttribute("login", services.findLoginById(id));
            return "login/updateLogin";
        }
        return "/index";

    }
    @PostMapping("/updateLogin")
    public String updateLogin(@ModelAttribute Login l){
        if(status=="Admin") {
            services.updateLogin(l.getBruger_id(), l);

            return "redirect:/viewLogin";
        }
        return "/index";
    }


    /**************************
     **************************
     ****----   Wages  ----****
     **************************
     **************************
     ___________________________________________________________________________*/

    @GetMapping("/viewWages")
    public String viewWages(Model model){
        if(status=="Admin") {
            List<Wage> wageList = services.getWagesThisMonth();
            model.addAttribute("wages", wageList);
            return "wage/wage";
        }
        return "/index";
    }

    @PostMapping("/viewWagesDateFromTo")
    public String viewWagesDateFromTo(@ModelAttribute DateFromTo dateFromTo, Model model){
        if(status=="Admin") {
            List<Wage> wageList = services.getWagesDateFromTo(dateFromTo.getDate(), dateFromTo.getDateTo());
            model.addAttribute("wages", wageList);
            return "wage/wage";
        }
        return "index";
    }
}