package com.registry.employeeregistry.controller;

import com.registry.employeeregistry.dto.Employee;
import com.registry.employeeregistry.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @DESCRIPTION This is a EmployeeController with all the for CRUD Operation
 * @METHODS GetMapping,PostMapping,PutMapping,DeleteMapping
 * @AUTHENTICATION Basic AUth
 * */
@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        MDC.put("transactionId", UUID.randomUUID().toString());
        List<Employee> employeeList=service.getAllEmployeeDetails();
        log.info("EventType={},transactionId={},message={}","getAllEmployee",
                MDC.get("transactionId"),"Fetched all employees data");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    /*@ApiOperation is used to provide more info on specific endpoints*/

    @GetMapping("/employee/{employeeId}")
    @ApiOperation(value = "Finds employee by ID",
            notes = "Provide and ID to fetch the details from database",
            response = Employee.class)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId){
        MDC.put("transactionId", UUID.randomUUID().toString());
        Employee employeeList=service.getEmployeeDetailsById(employeeId);
        log.info("EventType={},transactionId={},message={},EmployeeId={}","getEmployeeById",
                MDC.get("transactionId"),"Fetched employee data by Id",employeeId);
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<List<Employee>> getEmployeesByMultipleID(@PathVariable int[] employeeId){
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(new Employee()));
    }

    @PostMapping("/employees")
    public ResponseEntity<String> saveAllEmployees(@RequestBody List<Employee> employee){
        MDC.put("transactionId", UUID.randomUUID().toString());
        String response=service.saveAllEmployeesDetails(employee);
        log.info("EventType={},transactionId={},message={}","saveAllEmployees",
                MDC.get("transactionId"),"Saved all employees data");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployees(@RequestBody Employee employee){
        MDC.put("transactionId", UUID.randomUUID().toString());
        Employee response=service.updateEmployeesDetails(employee,employee.getEmployeeId());
        log.info("EventType={},transactionId={},message={}","updateEmployees",
                MDC.get("transactionId"),"update all employees data");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int employeeId){
        MDC.put("transactionId", UUID.randomUUID().toString());
        String response=service.deleteEmployeeById(employeeId);
        log.info("EventType={},transactionId={},message={}","deleteEmployeeById",
                MDC.get("transactionId"),"Deleted employees data by ID");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
