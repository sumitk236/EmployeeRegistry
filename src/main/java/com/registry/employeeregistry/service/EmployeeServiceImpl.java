package com.registry.employeeregistry.service;

import com.registry.employeeregistry.dto.Employee;
import com.registry.employeeregistry.repository.EmployeeRepository;
import com.registry.employeeregistry.repository.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployeeDetails() {
         return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeeDetailsById(int employeeId) {
        return null;
    }

    @Override
    public String saveAllEmployeesDetails(Employee employee) {
        return null;
    }

    @Override
    public String updateEmployees(Employee employee) {
        return null;
    }

    @Override
    public String deleteEmployeeById(int employeeId) {
        return null;
    }
}
