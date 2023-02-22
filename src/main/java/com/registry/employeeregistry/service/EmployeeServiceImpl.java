package com.registry.employeeregistry.service;

import com.registry.employeeregistry.dto.Employee;
import com.registry.employeeregistry.exception.EmployeeException;
import com.registry.employeeregistry.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Cacheable("employees")
    public List<Employee> getAllEmployeeDetails() {
        log.info("EventType={},transactionId={},message={}","getAllEmployeeDetails",
                MDC.get("transactionId"),"Fetched all employees data from Database");
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    @Cacheable(value = "employees", key = "#employeeId")
    public List<Employee> getEmployeeDetailsById(int employeeId){
        log.info("EventType={},transactionId={},message={},EmployeeId={}","getEmployeeDetailsById",
                MDC.get("transactionId"),"Fetched employee data by Id",employeeId);
       return Arrays.asList( employeeRepository.findById(employeeId).get());
    }

    @Override
    public String saveAllEmployeesDetails(List<Employee> employee) {
        Iterable<Employee> list=employeeRepository.saveAll(employee);
        log.info("EventType={},transactionId={},message={}","saveAllEmployeesDetails",
                MDC.get("transactionId"),"Saved all employees data");
        return "Records saved Successfully";
    }

    @Override
    public String updateEmployeesDetails(Employee employee) {
        employeeRepository.save(employee);
        log.info("EventType={},transactionId={},message={}","updateEmployees",
                MDC.get("transactionId"),"update all employees data");
        return "Records Updated Successfully";
    }

    @Override
    public String deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
        log.info("EventType={},transactionId={},message={}","updateEmployees",
                MDC.get("transactionId"),"Deleted employees data by Id"+employeeId);
        return "Record deleted Successfully";
    }
}
