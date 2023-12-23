package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.models.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeService {

     private ArrayList<Employee> employees = new ArrayList<>(Arrays.asList(
             new Employee(1,"Employe1","Employe1@email.com"),
             new Employee(2,"Employe2","Employe2@email.com"),
             new Employee(3,"Employe3","Employe3@email.com"),
             new Employee(4,"Employe4","Employe4@email.com"),
             new Employee(5,"Employe5","Employe5@email.com")
     ));

     public List<Employee> getEmployees(){
         return employees;
     }

     public Employee getById(int id){
         return employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
     }

     public String Delete(int id){
         employees.remove(id);
         return "Employe has deleted succecfly";
     }

}
