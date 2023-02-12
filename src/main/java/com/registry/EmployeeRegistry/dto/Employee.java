package com.registry.EmployeeRegistry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Employee {
    private int employeeId;
    private String employeeName;
    private String dept;
    private double salary;
}
