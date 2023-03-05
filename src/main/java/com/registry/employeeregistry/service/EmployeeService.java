package com.registry.employeeregistry.service;

import com.registry.employeeregistry.dto.Employee;

import java.util.List;

public interface EmployeeService {

    /*To get All Employee from the database*/
    List<Employee> getAllEmployeeDetails();

    /*To get employee detail using id from database*/
   Employee getEmployeeDetailsById(int employeeId);

    /*To save employee all details in database*/
    String saveAllEmployeesDetails(List<Employee> employee);

    /*To update specific Employee details*/
    Employee updateEmployeesDetails(Employee employee,int id);

    /*To delete any specific employee details*/
    String deleteEmployeeById(int employeeId);


}
