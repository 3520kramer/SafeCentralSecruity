package com.example.demo.Service;


import com.example.demo.Model.*;
import com.example.demo.Repository.*;
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
    @Autowired
    ScheduleRepo scheduleRepo;
    @Autowired
    LoginRepo loginRepo;
    @Autowired
    WageRepo wageRepo;

    public List<Customer> getAll(){
        return customerRepo.getAll();
    }

    public List<NewsFeed> getAllNewsFeed(){
        return newsFeedRepo.getAllNewsFeed();
    }

    public Customer addCustomer (Customer c){
        return customerRepo.addCustomer(c);
    }

    public Boolean deleteNewsFeed(int id){
        return newsFeedRepo.delete(id);
    }

    public Boolean deleteCustomer(int id) {
        return customerRepo.delete(id);
    }

    public NewsFeed createNewsFeed(NewsFeed newsFeed){
        return newsFeedRepo.createNewsFeed(newsFeed);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepo.getAllSchedules();
    }

    public List<Schedule> getTodaysSchedule(){
        return scheduleRepo.getTodaysSchedule();
    }

    public List<Schedule> getOneSchedule(String date){
        return scheduleRepo.getOneSchedule(date);
    }

    public List<Schedule> getScheduleDateFromTo(String date, String dateTo){
        return scheduleRepo.getScheduleDateFromTo(date, dateTo);
    }

    public Schedule createSchedule(Schedule schedule){
        return scheduleRepo.createSchedule(schedule);
    }

    public Schedule updateSchedule(Schedule schedule){
        return scheduleRepo.updateSchedule(schedule);
    }

    public double getHoursWorked(String startTidString, String slutTidString) {
        double timetal;
        String startTidTimeString = startTidString.substring(0,2);
        String slutTidTimeString = slutTidString.substring(0,2);

        double startTidTime = Double.parseDouble(startTidTimeString);
        double slutTidTime = Double.parseDouble(slutTidTimeString);

        if(slutTidTime < startTidTime){
            slutTidTime += 24;
        }

        timetal = slutTidTime - startTidTime;

        String startTidMinutString = startTidString.substring(3,5);
        String slutTidMinutString = slutTidString.substring(3,5);

        double startTidMinut = Double.parseDouble(startTidMinutString);
        double slutTidMinut = Double.parseDouble(slutTidMinutString);

        startTidMinut /= 60;
        slutTidMinut /= 60;

        timetal += slutTidMinut - startTidMinut;
        return timetal;
    }

    public Schedule findScheduleById(int schedule_id){
        return scheduleRepo.findScheduleById(schedule_id);
    }

    public Boolean deleteSchedule(int id){
        return scheduleRepo.delete(id);
    }

    public List<Login> getLogin(){
        return loginRepo.getLogin();
    }
    public List<Login> getAllLogins(){
        return loginRepo.getAll();
    }
    public Customer updateCustomer(int id, Customer c){
        return customerRepo.updateCustomer(id, c);
    }
    public Customer findCustomerById(int id) {
        return customerRepo.findCustomerById(id);

    }
    public Customer findCustomerByName(String firmaNavn) {
        return customerRepo.findCustomerByName(firmaNavn);
    }

    public Employee addEmployee(Employee e){
        return employeeRepo.addEmployee(e);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepo.getAllEmployees();
    }

    public Employee updateEmployee(int id, Employee e){
        return employeeRepo.updateEmployee(id, e);
    }

    public Employee findEmployeeByName(String firstName, String lastName) {
        return employeeRepo.findEmployeeByName(firstName, lastName);
    }
    public Employee findEmployeeById(int id) {
        return employeeRepo.findEmployeeById(id);
    }

    public Boolean deleteEmployee(int id){
        return employeeRepo.delete(id);
    }

    public List<Employee> getAllEmployeesNames(){
        return employeeRepo.getAllEmployeesName();
    }

    public List<Wage> getWagesThisMonth(){
        return wageRepo.getWagesThisMonth();
    }

    public List<Wage> getWagesDateFromTo(String dateFrom, String dateTo){
        return wageRepo.getWagesDateFromTo(dateFrom, dateTo);
    }




}

