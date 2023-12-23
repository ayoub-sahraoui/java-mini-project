package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.models.Employee;
import com.hotelbooking.hotelbooking.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeService employeService;
    @RequestMapping("/employe")
    public List<Employee> getEmployees(){
        return employeService.getEmployees();
    }

    @RequestMapping("/employe/find/{id}")
    public Employee getById(@PathVariable int id){
        return employeService.getById(id);
    }

    @RequestMapping("employe/{id}")
    public String delete(@PathVariable int id){
        return employeService.Delete(id);
    }
}
