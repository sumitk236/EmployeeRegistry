package com.registry.employeeregistry.service;

import com.registry.employeeregistry.dto.Confirmation;
import com.registry.employeeregistry.dto.Employee;
import com.registry.employeeregistry.exception.EmployeeException;
import com.registry.employeeregistry.repository.ConfirmationRepository;
import com.registry.employeeregistry.repository.EmployeeRepository;
import com.registry.employeeregistry.validation.EmployeeValidation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ConfirmationRepository confirmationRepository;

    @Autowired
    RetryTemplate retryTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    EmployeeValidation validation;

    @Override
    //@Cacheable("employees")
    public List<Employee> getAllEmployeeDetails() {
        log.info("EventType={},transactionId={},message={}","getAllEmployeeDetails",
                MDC.get("transactionId"),"Fetched all employees data from Database");

        List<Employee> employeeList=(List<Employee>) employeeRepository.findAll();

        /*Description-Calling another Company microservice to update salary of all employees
        * Process- The call will be made using retryTemplate and restTemplate.
        * */

       /* retryTemplate.execute(context -> {
            return callCompanyService(employeeList);
        });*/

        return employeeList;
    }

    private List<Employee> callCompanyService(List<Employee> employeeList) {
        HttpHeaders headers=new HttpHeaders();
        HttpEntity<List<Employee>> request = new HttpEntity<>(employeeList, headers);


        ResponseEntity<List<Employee>> updatedResponse=restTemplate.exchange("http://localhost:8082/company/employees", HttpMethod.POST,request, new ParameterizedTypeReference<List<Employee>>() {
        } );
        return updatedResponse.getBody();
    }

    @Override
    //@Cacheable(value = "employees", key = "#employeeId")
    public Employee getEmployeeDetailsById(int employeeId){
        log.info("EventType={},transactionId={},message={},EmployeeId={}","getEmployeeDetailsById",
                MDC.get("transactionId"),"Fetched employee data by Id",employeeId);
       return employeeRepository.findById(employeeId).get();
    }

    @Override
    @Transactional(noRollbackFor = EmployeeException.class)
    public String saveAllEmployeesDetails(List<Employee> employee) {
        validation.validateEmployeeDetails(employee);
        Iterable<Employee> list=employeeRepository.saveAll(employee);
        log.info("EventType={},transactionId={},message={}","saveAllEmployeesDetails",
                MDC.get("transactionId"),"Saved all employees data");
        if(employee==null)
        throw new EmployeeException("Error Occur before saving confirmation");
        List<Confirmation> confirmationList=employee.stream().map(each->convertToConfirmationObject(each)).collect(Collectors.toList());
        confirmationRepository.saveAll(confirmationList);
        return "Records saved Successfully";
    }

    private Confirmation convertToConfirmationObject(Employee employee){
        Confirmation confirmation=new Confirmation();
        confirmation.setId(employee.getEmployeeId());
        confirmation.setStatus("Success");
        return confirmation;
    }

    @Override
    //@CacheEvict(value = "employees", key = "#employeeId")
    //@CachePut(value = "employees", key="#employeeId")
    public Employee updateEmployeesDetails(Employee employee,int employeeId) {
        employeeRepository.save(employee);
        log.info("EventType={},transactionId={},message={}","updateEmployees",
                MDC.get("transactionId"),"update all employees data");
        return employee;
    }

    @Override
    public String deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
        log.info("EventType={},transactionId={},message={}","updateEmployees",
                MDC.get("transactionId"),"Deleted employees data by Id"+employeeId);
        return "Record deleted Successfully";
    }
}
