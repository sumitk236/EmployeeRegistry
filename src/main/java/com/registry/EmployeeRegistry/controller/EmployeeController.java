package com.registry.EmployeeRegistry.controller;

import com.registry.EmployeeRegistry.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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
@Slf4j
public class EmployeeController {

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        MDC.put("transactionId", UUID.randomUUID().toString());
        log.info("EventType={},transactionId={},message={}","getAllEmployee",
                MDC.get("transactionId"),"Fetched all employees data");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(new Employee()));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable int employeeId){
        MDC.put("transactionId", UUID.randomUUID().toString());
        log.info("EventType={},transactionId={},message={},EmployeeId={}","getEmployeeById",
                MDC.get("transactionId"),"Fetched employee data by Id",employeeId);
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(new Employee()));
    }

    @PostMapping("/employees")
    public ResponseEntity<String> saveAllEmployees(@RequestBody Employee employee){
        MDC.put("transactionId", UUID.randomUUID().toString());
        log.info("EventType={},transactionId={},message={}","saveAllEmployees",
                MDC.get("transactionId"),"Saved all employees data");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/employees")
    public ResponseEntity<String> updateEmployees(@RequestBody Employee employee){
        MDC.put("transactionId", UUID.randomUUID().toString());
        log.info("EventType={},transactionId={},message={}","updateEmployees",
                MDC.get("transactionId"),"update all employees data");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/employees")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int employeeId){
        MDC.put("transactionId", UUID.randomUUID().toString());
        log.info("EventType={},transactionId={},message={}","updateEmployees",
                MDC.get("transactionId"),"update all employees data");
        MDC.clear();
        return ResponseEntity.status(HttpStatus.OK).body("Record deleted");
    }

}
