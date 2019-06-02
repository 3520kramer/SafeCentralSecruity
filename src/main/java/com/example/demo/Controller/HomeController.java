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
// Ansvarlige: Kasper, Mads, Nadia og Oliver


    // String som bruges til login
    private String status = "Admin";

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
    som går igennem vores Logins fra databasen og sammenligner med de indtastede værdier i gennem vores "lo"
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
      if(status.equalsIgnoreCase("Admin")) {
          System.out.println("Getmapping - createnewsfeed" + status);
          return "newsfeed/createNewsfeed";
      } else if (status.equalsIgnoreCase("User")){
          System.out.println("Getmapping - createnewsfeed" + status);
          return "/homeUser";
      }
    return "/index";
    }


/*
Postmapping hvor vi tager newsfeed med som parameter, hvor vi kalder createNewsFeed, hvori vi bruger newsfeed parameter værdi

Vi tester også lige om du har rettigheder til at tilgå siden
 */
    @PostMapping("/createNewsfeed")
    public String createNewsFeed(NewsFeed newsFeed) {
        if (status.equalsIgnoreCase("Admin")) {
            services.createNewsFeed(newsFeed);
            System.out.println("postmapping createnewsfeed" + status);
            return "redirect:/home";
        }
        System.out.println("postmapping createnewsfeed" + status);
        return "/index";
    }


/*
Getmapping hvor vi tager id, som int med over og bruger det til at køre vores deletenewsfeed.
 */
    @GetMapping("/delete_news/{id}")
    public String deleteNewsFeed(@PathVariable("id")int id){
        if(status.equalsIgnoreCase("Admin")) {
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




    @GetMapping("/viewCustomer")
    public String viewCustomer(Model model) {
        if(status.equalsIgnoreCase("Admin")) {
            List<Customer> customerList = services.getAll();
            model.addAttribute("customers", customerList);
            return "customer/viewCustomer";
        }
        return "/index";
    }

    /*
    Postmapping hvor vi bruger customer klassen, som parameter, til at oprette en ny customer

    Samme opbygning bliver brugt på nedenstående create funktioner, bare med andre parameter og create metode kald
     */

    @PostMapping("/createCustomer")
    public String create(Customer customer) {
        if(status.equalsIgnoreCase("Admin")) {
            services.addCustomer(customer);
            return "redirect:/viewCustomer";
        }
        return "/index";
    }

/*
Getmapping hvor vi tager id, som int med over og bruger det til at køre vores deleteCustomer.

 Samme opbygning bliver brugt på nedenstående delete funktioner, bare med andre parameter og delete metode kald
 */

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {

        if (status.equalsIgnoreCase("Admin")) {
            boolean deleted = services.deleteCustomer(id);
            if (deleted) {
                return "redirect:/viewCustomer";
            } else {
                return "redirect:/viewCustomer";
            }
        }
        return "/index";
    }

    /*
    Getmapping hvor vi tager id, som int med over og bruger det til at køre vores updateCustomer.

     Samme opbygning bliver brugt på nedenstående update funktioner, bare med andre parameter og update metode kald
     */

    @GetMapping("/updateCustomer/{id}")
    public String update(@PathVariable("id") int id, Model model){
        if(status.equalsIgnoreCase("Admin")) {
            model.addAttribute("customer", services.findCustomerById(id));
            return "customer/updateCustomer";
        }
        return "/index";

    }
    /*
    Postmapping af update customer, til at videre sende kunde_id, sådan så vi kan redigere den rigtige kunde

    Samme opbygning bliver brugt på nedenstående update funktioner, bare med andre parameter og update metode kald
     */
    @PostMapping("/updateCustomer")
    public String update(Customer customer){
        if(status.equalsIgnoreCase("Admin")) {
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





    @GetMapping("/viewEmployee")
    public String viewEmployee(Model model) {
        if(status.equalsIgnoreCase("Admin")) {
            List<Employee> employeeList = services.getAllEmployees();
            model.addAttribute("employees", employeeList);
            return "employee/viewEmployee";
        }
        return "/index";
    }

    @PostMapping("/createEmployee")
    public String createEmployee(Employee e) {
       if(status.equalsIgnoreCase("Admin")) {
           services.addEmployee(e);
           return "redirect:/viewEmployee";
       }
       return "/index";
    }


    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        if (status.equalsIgnoreCase("Admin")) {
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
        if(status.equalsIgnoreCase("Admin")) {
            model.addAttribute("employee", services.findEmployeeById(id));
            return "employee/updateEmployee";
        }
        return "/index";
    }
    @PostMapping("/updateEmployee")
    public String updateEmployee(Employee e){
        if(status.equalsIgnoreCase("Admin")){
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



/*
Getmapping for viewSchedule, hvor vi viser en html side baseret på hvad status er

Selvom metode kaldene er ens, er det gjort for at få forskellige menuer senere hen
 */

    @GetMapping("/viewSchedule")
    public String viewTodaysSchedule(Model model){
        if(status.equalsIgnoreCase("Admin")){
            List<Schedule> scheduleList = services.getTodaysSchedule();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status.equalsIgnoreCase("User")){
            List<Schedule> scheduleList = services.getTodaysSchedule();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "redirect:/index";
        }
    }

    /*
Postmapping til at få vist vagtplan for en dato, vi bruger klassen dateFromTo, som parameter
til at få fat i dato.
 */

    @PostMapping("/viewScheduleDate")
    public String viewScheduleDate(DateFromTo dateFromTo, Model model){
        if(status.equalsIgnoreCase("Admin")){
            List<Schedule> scheduleList = services.getOneSchedule(dateFromTo.getDate());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status.equalsIgnoreCase("User")){
            List<Schedule> scheduleList = services.getOneSchedule(dateFromTo.getDate());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "redirect:/index";
        }
    }
    /*
        Postmapping til at få vist vagtplan indenfor en periode, vi bruger DateFromTo klassen, som parameter
        til at have et sted at lægge 2 forskellige dato´er og kigge imellem dem og inklusiv dem
         */
    @PostMapping("/viewScheduleDateFromTo")
    public String viewScheduleDateFromTo(@ModelAttribute DateFromTo dateFromTo, Model model){
        if(status.equalsIgnoreCase("Admin")){
            List<Schedule> scheduleList = services.getScheduleDateFromTo(dateFromTo.getDate(), dateFromTo.getDateTo());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status.equalsIgnoreCase("User")){
            List<Schedule> scheduleList = services.getScheduleDateFromTo(dateFromTo.getDate(), dateFromTo.getDateTo());
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "/index";
        }
    }

    @GetMapping("/viewScheduleAll")
    public String viewScheduleAll(Model model){
        if(status.equalsIgnoreCase("Admin")){
            List<Schedule> scheduleList = services.getAllSchedules();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewSchedule";

        }else if(status.equalsIgnoreCase("User")){
            List<Schedule> scheduleList = services.getAllSchedules();
            model.addAttribute("schedules", scheduleList);
            return "schedule/viewScheduleUser";

        }else{
            return "/index";
        }
    }

    /*
    createSchedule getmapping hvor vi først sikre os at det er en admin der bruger siden, derefter laver vi en liste med
    vores kunder og medarbejder, hvorefter vi smider det i en model.addattribute til senere hen at bruge det igennem thymeleaf
    på siden med en liste af valgmuligheder.
     */
   @GetMapping("/createSchedule")
    public String createSchedule(Model model){
        if(status.equalsIgnoreCase("Admin")) {
            List<Employee> employeeList = services.getAllEmployeesNames();
            model.addAttribute("employees", employeeList);

            List<Customer> customerList = services.getAll();
            model.addAttribute("customers", customerList);

            return "schedule/createSchedule";
        }
        return "/index";
    }

    /*
Vi indhenter schedule som parameter, og starter med at udregne timetal, baseret på brugerens indtastede tidspunkter
Vi tager derefter fat i en kunde og gør schedules kunde id = med den fundne kundes id, sådan vi kan bruge det senere

Derefter laver vi et array til at splitte 1 string fra hinanden til fornavn og efternavn. Dette er nødvendigt, da vi bruger fornavn og efternavn sammensat på siden.
Dette bliver brugt til at hente medarbejder id, som skal bruges senere

Og til sidst kører vi så createschedule med de fundne id´er fra medarbejder og kunden, samt tid og dato.
     */
    @PostMapping("/createSchedule")
    public String createSchedule(Schedule schedule){
        if(status.equalsIgnoreCase("Admin")) {
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

    /*
    Samme som de andre updates, bare med flere lister der bliver hentet som kan opdateres hvis nødvendigt
     */
    @GetMapping("/update_schedule/{schedule_id}")
    public String updateSchedule(@PathVariable("schedule_id") int schedule_id, Model model){
        if(status.equalsIgnoreCase("Admin")) {
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
        if(status.equalsIgnoreCase("Admin")) {
            boolean deleted = services.deleteSchedule(schedule_id);

            return "redirect:/viewScheduleAll";
        }
        return "index";
    }

    /*
    Samme update som de andre, bare hvor der er behov for flere objekter der bliver ændre
    Tilmed et behov for at splitte navn af, til at få fat på den rigtige medarbejder
     */

    @PostMapping("/update_schedule")
    public String updateSchedule(Schedule schedule){
        if(status.equalsIgnoreCase("Admin")) {
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
        if(status.equalsIgnoreCase("Admin")) {
            List<Login> loginList = services.getAllLogins();
            model.addAttribute("logins", loginList);
            return "login/viewLogin";
        }
        return "/index";
    }


    @PostMapping("/createLogin")
    public String create(Login login) {
        if(status.equalsIgnoreCase("Admin")) {
            services.addLogin(login);
            System.out.println("Login runs");
            return "redirect:/viewLogin";

        }
        return "/index";
    }

    @GetMapping("/deleteLogin/{id}")
    public String deleteLogin(@PathVariable("id") int id) {

        if (status.equalsIgnoreCase("Admin")) {
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
        if(status.equalsIgnoreCase("Admin")) {
            model.addAttribute("login", services.findLoginById(id));
            return "login/updateLogin";
        }
        return "/index";

    }
    @PostMapping("/updateLogin")
    public String updateLogin(Login l){
        if(status.equalsIgnoreCase("Admin")) {
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
        if(status.equalsIgnoreCase("Admin")) {
            List<Wage> wageList = services.getWagesThisMonth();
            model.addAttribute("wages", wageList);
            return "wage/wage";
        }
        return "/index";
    }

    /*
    Normal view funktion, bare med tilføjet datefromto klasse som parameter, til at søge indenfor en bestemt periode
     */
    @PostMapping("/viewWagesDateFromTo")
    public String viewWagesDateFromTo(DateFromTo dateFromTo, Model model){
        if(status=="Admin") {
            List<Wage> wageList = services.getWagesDateFromTo(dateFromTo.getDate(), dateFromTo.getDateTo());
            model.addAttribute("wages", wageList);
            return "wage/wage";
        }
        return "index";
    }
}